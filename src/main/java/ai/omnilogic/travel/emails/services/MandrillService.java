package ai.omnilogic.travel.emails.services;

import ai.omnilogic.travel.emails.enums.ExchangeType;
import ai.omnilogic.travel.emails.enums.StatusEmail;
import ai.omnilogic.travel.emails.exceptions.SendEmailException;
import ai.omnilogic.travel.emails.models.Message;
import ai.omnilogic.travel.emails.models.MessageStatus;
import ai.omnilogic.travel.emails.models.log.LogEmail;
import ai.omnilogic.travel.emails.models.mail.Mail;
import ai.omnilogic.travel.emails.repositories.LogEmailRepository;
import ai.omnilogic.travel.emails.utils.Utils;
import com.bugsnag.Bugsnag;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microtripit.mandrillapp.lutung.MandrillApi;
import com.microtripit.mandrillapp.lutung.model.MandrillApiError;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage;
import com.microtripit.mandrillapp.lutung.view.MandrillMessageInfo;
import com.microtripit.mandrillapp.lutung.view.MandrillMessageStatus;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MandrillService extends AbstractPlayerService implements IPlayerService{

    public static final String MANDRILLAPP_COM = "mandrillapp.com";
    public static final int TEN_MINUTES = 60 * 1000 * 10;
    public static final int THIRTY_MINUTES = 60 * 1000 * 30;
    public static final int ONE_HOUR = 60 * 1000 * 60;
    public static final int THREE_MINUTES = 60 * 1000 * 3;
    public static final int NOW = 1;

    private final MandrillApi mandrillApi;

    private final Bugsnag bugsnag;

    private final LogEmailService logEmailService;

    private final AmqpTemplate queueSender;

    private final LogEmailRepository logEmailRepository;

    public MandrillService(MandrillApi mandrillApi,
                           Bugsnag bugsnag,
                           LogEmailService logEmailService,
                           AmqpTemplate queueSender,
                           LogEmailRepository logEmailRepository) {
        this.mandrillApi = mandrillApi;
        this.bugsnag = bugsnag;
        this.logEmailService = logEmailService;
        this.queueSender = queueSender;
        this.logEmailRepository = logEmailRepository;
    }

    @Override
    public List<MessageStatus> send(Map<String, String> msg, Mail mailModel) throws SendEmailException {
        prepareMessage(msg);
        Message<MandrillMessage> message = new Message<>(new MandrillMessage());
        message.setSigningDomain(MANDRILLAPP_COM);
        message = generateMail(msg, message);
        List<MessageStatus> result = null;
        try{
            result = deliverMail(message);
            List<String> mandrillIds = result.stream().map(i -> i.getId()).collect(Collectors.toList());
            Boolean isSent = result.stream().map(item -> item.getStatus()).findFirst().orElse(StatusEmail.FAILED).equals(StatusEmail.SENT);
            logEmailService.sent(mailModel,
                    isSent,
                    result.stream().map(MessageStatus::getStatus).findAny().orElse(StatusEmail.OTHERS).getName(),
                    mandrillIds);
            //if (!isSent)
            //    sendQueueEmailSyncInfo(THREE_MINUTES, Map.of(mailModel.getHotelCode(), mandrillIds), getRoutingByHotel(mailModel));
        } catch (SendEmailException ex) {
            log.error(String.format("Error send email >> %s", ex.getMessage()));
            throw ex;
        }
        return result;
    }

    private void sendQueueEmailSyncInfo(int waitingTimeInSeconds, Map<Integer, List<String>> mailModel, String routing) {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader("x-delay", waitingTimeInSeconds);
        try {
            org.springframework.amqp.core.Message messageAMQ = new org.springframework.amqp.core.Message(new ObjectMapper().writer().writeValueAsString(mailModel).getBytes(), messageProperties);
            queueSender.convertAndSend(ExchangeType.AMQ_CHECK_GENERIC.getExchange(),
                    routing,
                    messageAMQ);
        } catch (Exception ex) {
            log.error(String.format("Error sendQueueEmailSyncInfo Routing %s >> ", routing, ex.getMessage()));
            sendQueueEmailSyncInfo(ONE_HOUR, mailModel, ExchangeType.AMQ_CHECK_GENERIC.getRouting(), false, null);
        }
    }

    private void sendQueueEmailSyncInfo(int waitingTimeInSeconds, Map<Integer, List<String>> mailModel, String routing, Boolean checkIfOld, LogEmail logEmail) {
        if ((checkIfOld && !isOld(logEmail)) || !checkIfOld)
            sendQueueEmailSyncInfo(waitingTimeInSeconds, mailModel, routing);
    }

    @Override
    public void verifyAndSyncInfo(Map<Integer, List<String>> messageIds) throws SendEmailException {
        messageIds.entrySet().stream()
            .forEach(hotel -> {
                String id = hotel.getValue().stream().findFirst().orElse("");
                Integer hotelCode = Integer.parseInt(String.valueOf(hotel.getKey()));
                LogEmail logEmail = logEmailRepository.findByMandrillId(hotelCode, id).stream().findFirst().get();
                try {
                    MandrillMessageInfo info = null;
                    try {
                        info = mandrillApi.messages().info(id);
                    } catch (Exception ex) {
                        log.warn(String.format("Mandrill API error. Ids %s >> %s ", messageIds, ex.getMessage()));
                        sendQueueEmailSyncInfo(THIRTY_MINUTES, messageIds, getRoutingByHotel(hotelCode), true, logEmail);
                        return;
                    }
                    if (Optional.ofNullable(info).isPresent() &&
                            info.getState().equals(StatusEmail.SENT.getName())) {
                        saveLogEmail(logEmail, true, info);
                    } else {
                        saveLogEmail(logEmail, false, info);
                        if (info.getState().equals(StatusEmail.REJECTED.getName()) ||
                                info.getState().equals(StatusEmail.FAILED.getName()))
                            return;
                        sendQueueEmailSyncInfo(TEN_MINUTES, messageIds, getRoutingByHotel(hotelCode), true, logEmail);
                    }
                } catch (Exception ex) {
                    log.warn(String.format("Error verifyAndSyncInfo. Ids %s >> %s ", messageIds, ex.getMessage()));
                    sendQueueEmailSyncInfo(ONE_HOUR, messageIds, ExchangeType.AMQ_CHECK_GENERIC.getRouting(), true, logEmail);
                }
            });
    }

    private void saveLogEmail(LogEmail logEmail, boolean sent, MandrillMessageInfo info) {
        logEmail.setSent(sent);
        logEmail.setStatusExternal(info.getState());
        logEmailRepository.save(logEmail);
    }

    private boolean isOld(LogEmail logEmail) {
        Objects.requireNonNull(logEmail, "Param logEmail must be not null");
        Objects.requireNonNull(logEmail.getDate(), "Param logEmail.getDate() must be not null");
        LocalDate createDate = Utils.stringToDate(logEmail.getDate(), DateTimeFormatter.ISO_DATE_TIME);
        LocalDate dateNow = Utils.stringToDate(Utils.dateNow(), DateTimeFormatter.ISO_DATE_TIME).minusDays(2);
        return createDate.isBefore(dateNow);
    }

    private String getRoutingByHotel(Mail mailModel) {
        if (!Optional.ofNullable(mailModel.getHotelCode()).isPresent())
            return "routing-generic";

        return getRoutingByHotel(mailModel.getHotelCode());
    }

    private String getRoutingByHotel(Integer hotelCode) {
        switch (hotelCode) {
            case 1:
                return ExchangeType.AMQ_CHECK_CAETE.getRouting();
            case 2:
                return ExchangeType.AMQ_CHECK_ATIBAIA.getRouting();
            case 3:
                return ExchangeType.AMQ_CHECK_ARAXA.getRouting();
            case 6:
                return ExchangeType.AMQ_CHECK_ALEXANIA.getRouting();
            case 9:
                return ExchangeType.AMQ_CHECK_ALEGRO.getRouting();
            default:
                return ExchangeType.AMQ_CHECK_GENERIC.getRouting();
        }
    }

    private List<MessageStatus> deliverMail(Message<MandrillMessage> message) throws SendEmailException {

        Date sendAt = Date.from(Instant.now());
        MandrillMessageStatus[] result = new MandrillMessageStatus[0];
        List<MessageStatus> resultMessages = new ArrayList<>();
        try {
            MandrillMessage mandrillMessage = getMandrillMessage(message);

            result = mandrillApi.messages().send(mandrillMessage, false, "Main Pool", sendAt);

            if(result == null || result.length == 0 || !Optional.ofNullable(result[0].getStatus()).isPresent()){
                throw new SendEmailException(String.format("ERROR SEND E-MAILS %s", message));
            }

            Arrays.stream(result)
                    .forEach(item -> {
                        MessageStatus messageStatus = new MessageStatus();
                        messageStatus.setStatus(StatusEmail.getByName(item.getStatus()));
                        messageStatus.setId(item.getId());
                        messageStatus.setEmail(item.getEmail());
                        messageStatus.setRejectReason(item.getRejectReason());
                        resultMessages.add(messageStatus);
                    });

            return resultMessages;

        } catch (SendEmailException e) {
            log.error("A mandrill error occurred: " + e.getMessage());
            bugsnag.notify(e);
            throw e;
        } catch (MandrillApiError e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static MandrillMessage getMandrillMessage(Message<MandrillMessage> message) {
        MandrillMessage mandrillMessage = new MandrillMessage();
        mandrillMessage.setAttachments(message.getAttachments());
        mandrillMessage.setAutoHtml(message.getAutoHtml());
        mandrillMessage.setFromName(message.getFromName());
        mandrillMessage.setFromEmail(message.getFromEmail());
        mandrillMessage.setHtml(message.getHtml());
        mandrillMessage.setHeaders(message.getHeaders());
        mandrillMessage.setAutoText(message.getAutoText());
        mandrillMessage.setBcc(message.getBccAddress());
        mandrillMessage.setSigningDomain(message.getSigningDomain());
        mandrillMessage.setSubject(message.getSubject());
        mandrillMessage.setTo(message.getTo());
        return mandrillMessage;
    }

}

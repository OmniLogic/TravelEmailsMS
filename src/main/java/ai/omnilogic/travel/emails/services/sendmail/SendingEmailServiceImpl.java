package ai.omnilogic.travel.emails.services.sendmail;

import ai.omnilogic.travel.emails.enums.ExchangeType;
import ai.omnilogic.travel.emails.models.hotel.HotelType;
import ai.omnilogic.travel.emails.models.mail.Mail;
import br.com.omnilogic.javautils.utils.Serializer;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.util.*;

@Service
public class SendingEmailServiceImpl implements SendingEmailService{

    private static Logger log = LoggerFactory.getLogger(SendingEmailServiceImpl.class);

    private final AmqpTemplate queueSender;

    @Value("${taua.email.from}")
    private static String TAUA_EMAIL_FROM;

    @Value("${taua.email.from-araxa}")
    private static String TAUA_EMAIL_FROM_ARAXA;

    @Value("${taua.email.replay-to}")
    private static String TAUA_EMAIL_REPLAYTO;

    @Value("${taua.email.replay-to-araxa}")
    private static String TAUA_EMAIL_REPLAYTO_ARAXA;

    @Autowired
    @Qualifier("emailConfigBean")
    private Configuration emailConfig;

    public SendingEmailServiceImpl(AmqpTemplate queueSender) {
        this.queueSender = queueSender;
    }

    public void sendMail(Mail mailModel, String templateEmail) throws IOException, TemplateException {
        Template template = emailConfig.getTemplate(templateEmail);
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, mailModel.getModel());

        List<String> emails = new ArrayList<>();

        emails.add(mailModel.getTo());
        emails.add(mailModel.getCc());

        Map<String, String> mandrillMsg = new HashMap<>();
        mandrillMsg.put("to", mailModel.getTo());
        mandrillMsg.put("cc", mailModel.getCc());
        mandrillMsg.put("bcc", mailModel.getBcc());
        mandrillMsg.put("from", String.format("%s <%s>", mailModel.getHotelCode() != null && mailModel.getHotelCode().equals(HotelType.ARAXA.getCode()) ? "Grande Hotel de Araxá" : "Tauá Resorts", mailModel.getFrom()));
        mandrillMsg.put("encoding", "UTF-8");
        mandrillMsg.put("alt_encoding", "UTF-8");
        mandrillMsg.put("content_type", "text/html");
        mandrillMsg.put("alt_content_type", "text/plain");
        mandrillMsg.put("subject", mailModel.getSubject());
        mandrillMsg.put("body", html);
        mandrillMsg.put("reply_to", mailModel.getReplayTo());

        mailModel.setTemplate(Optional.ofNullable(templateEmail).orElse(""));

        try {
            String payload = String.join("|", Serializer.toJson(mandrillMsg), Serializer.toJson(mailModel));
            queueSender.convertAndSend(ExchangeType.AMQ_SEND_GENERIC.getExchange(), getRoutingByHotel(mailModel), payload);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(String.format("ERROR SENT EMAIL MESSAGING RESERVATION ID %s >> %s",
                    Optional.ofNullable(mailModel.getReserveId()).orElse(""),
                    e.getMessage()));
        }
    }

    private static String getRoutingByHotel(Mail mailModel) {

        if (!Optional.ofNullable(mailModel.getHotelCode()).isPresent())
            return "routing-generic";

        HotelType hotel = HotelType.getNameByCode(mailModel.getHotelCode());
        switch (hotel) {
            case CAETE:
                return ExchangeType.AMQ_SEND_CAETE.getRouting();
            case ATIBAIA:
                return ExchangeType.AMQ_SEND_ATIBAIA.getRouting();
            case ARAXA:
                return ExchangeType.AMQ_SEND_ARAXA.getRouting();
            case ALEXANIA:
                return ExchangeType.AMQ_SEND_ALEXANIA.getRouting();
            case ALEGRO:
                return ExchangeType.AMQ_SEND_ALEGRO.getRouting();
            default:
                return ExchangeType.AMQ_SEND_GENERIC.getRouting();
        }
    }

    @Override
    public void sendGenericMail(Mail mailModel) throws IOException, TemplateException {

        Map model = new HashMap();
        defineAraxaOrNo(mailModel, mailModel.getHotelCode());
        model.put("name", mailModel.getName());
        model.put("subject", mailModel.getSubject());
        model.put("content", mailModel.getContent());
        mailModel.setModel( model );

        if (mailModel.getHotelCode() != null && Objects.equals(mailModel.getHotelCode(), HotelType.ARAXA.getCode()))
            sendMail(mailModel, "emailGenericAraxa.ftl");
        else
            sendMail(mailModel, "emailGeneric.ftl");
    }

    public static void defineAraxaOrNo(Mail mail, Integer hotelCode) {
        if (hotelCode != null && hotelCode.equals(HotelType.ARAXA.getCode())) {
            mail.setFrom(TAUA_EMAIL_FROM_ARAXA);
            mail.setReplayTo(TAUA_EMAIL_REPLAYTO_ARAXA);
        } else {
            mail.setFrom(TAUA_EMAIL_FROM);
            mail.setReplayTo(TAUA_EMAIL_REPLAYTO);
        }
    }
}

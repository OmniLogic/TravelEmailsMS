package ai.omnilogic.travel.emails.services.error;

import ai.omnilogic.travel.emails.dto.UserNotificationDTO;
import ai.omnilogic.travel.emails.dto.reservation.ReservationDTO;
import ai.omnilogic.travel.emails.enums.AgeGroup;
import ai.omnilogic.travel.emails.models.UserNotification;
import ai.omnilogic.travel.emails.models.hotel.HotelType;
import ai.omnilogic.travel.emails.models.mail.Mail;
import ai.omnilogic.travel.emails.services.sendmail.SendingEmailService;
import ai.omnilogic.travel.emails.services.sendmail.SendingEmailServiceImpl;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class SendingEmailErrorServiceImpl implements  SendingEmailErrorService{

    @Value("${taua.url.site}")
    private String URL_SITE_TAUA_RESERVES;

    @Value("${taua.url.site-araxa}")
    private String URL_SITE_ARAXA_RESERVES;

    @Value("${taua.url.search}")
    private String URL_SEARCH_TAUA_RESERVES;

    @Value("${taua.url.search-araxa}")
    private String URL_SEARCH_ARAXA_RESERVES;

    private final SendingEmailService sendingEmailService;

    public SendingEmailErrorServiceImpl(SendingEmailService sendingEmailService) {
        this.sendingEmailService = sendingEmailService;
    }

    @Override
    public void sendErrorPaymentCreditCardMail(ReservationDTO reservation) {
        try {
            Mail mail = new Mail();
            sendingEmailService.defineAraxaOrNo(mail, reservation.getHotelCode());
            mail.setHotelCode(reservation.getHotelCode());
            mail.setReserveId(reservation.getReserveId());
            mail.setTo(reservation.getCustomer().getEmail());
            mail.setName(reservation.getCustomer().getName());
            mail.setSubject("Tivemos um problema com seu cartão.");
            mail.setName(reservation.getCustomer().getFullName());

            Map<String, Object> model = new HashMap<>();

            model.put("name", reservation.getCustomer().getFirstName());
            model.put("link_buy_back", makeLinkBuyBack(reservation));
            mail.setModel(model);

            if (Objects.equals(reservation.getHotelCode(), HotelType.ARAXA.getCode()))
                sendingEmailService.sendMail(mail, "emailOrderFailAraxa.ftl");
            else
                sendingEmailService.sendMail(mail, "emailOrderFail.ftl");
        }  catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendErrorPaymentPixMail(ReservationDTO reservation) {
        try{
            Mail mail = new Mail();
            sendingEmailService.defineAraxaOrNo(mail, reservation.getHotelCode());
            mail.setHotelCode(reservation.getHotel().getCode());
            mail.setReserveId(reservation.getReserveId());
            mail.setTo(reservation.getCustomer().getEmail());
            mail.setName(reservation.getCustomer().getName());
            mail.setSubject("Seu PIX expirou");
            mail.setName( reservation.getCustomer().getFullName());

            Map<String, Object> model = new HashMap<>();

            model.put("name", reservation.getCustomer().getFirstName());
            model.put("link_buy_back", makeLinkBuyBack(reservation));
            mail.setModel(model);

            if (Objects.equals(reservation.getHotelCode(), HotelType.ARAXA.getCode()))
                sendingEmailService.sendMail(mail, "emailOrderFailPixAraxa.ftl");
            else
                sendingEmailService.sendMail(mail, "emailOrderFailPix.ftl");
        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendNotificationErrorMail(UserNotificationDTO userNotification) {

        try{
            if (userNotification.getUserNotifications().isEmpty())
                return;

            Mail email = new Mail();
            sendingEmailService.defineAraxaOrNo(email, HotelType.CAETE.getCode());
            email.setSubject(String.format("ERRO COMUNICAÇÃO COM SERVIDOR DO VOLUX"));
            Optional<String> firstEmail = userNotification.getUserNotifications().stream().map(UserNotification::getEmail).findFirst();
            AtomicReference<String> otherEmailCC = new AtomicReference<>("");
            userNotification.getUserNotifications().stream().filter(userNotificationItem -> !userNotificationItem.getEmail().equals(firstEmail.get())).forEach(item->{
                otherEmailCC.set(otherEmailCC + "," + item.getEmail());
            });
            email.setTo(firstEmail.get());
            email.setName("Aplication");
            email.setHotelCode(userNotification.getUserNotifications()
                    .stream().findFirst().get()
                    .getHotelCode()
                    .stream().findFirst().get());
            if(!otherEmailCC.get().trim().isEmpty())
                email.setCc(otherEmailCC.get().substring(1));
            email.setContent("Após algumas tentativas de comunicação com o sistema Volux, não tivemos sucesso na integração da reserva de número: "+ userNotification.getReserveId()+ ".");


            sendingEmailService.sendGenericMail(email);
        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
    }

    private String makeLinkBuyBack(ReservationDTO reservation) {
        AtomicInteger qtdChildren = new AtomicInteger(0);
        AtomicInteger qtdAdults = new AtomicInteger(0);
        List<String> parameters = new ArrayList<>();
        parameters.add("hotel=".concat(reservation.getHotel().getCode().toString()));
        parameters.add("checkin=".concat(reservation.getHotel().getStayDateStart()));
        parameters.add("checkout=".concat(reservation.getHotel().getStayDateEnd()));
        boolean hasChildren = reservation.getItems().stream().anyMatch(item -> item.getChildrens() > 0);
        if (hasChildren) {
            reservation.getItems().forEach(item -> {
                qtdChildren.addAndGet(item.getChildrens());
                qtdAdults.addAndGet(item.getAdults());
                item.getGuests().forEach(guest -> {
                    if (guest.getType().equals(AgeGroup.CHILDREN))
                        parameters.add("idades=".concat(guest.getAge()));
                });
            });
        }
        parameters.add("adults=".concat(qtdAdults.toString()));
        parameters.add("children=".concat(qtdChildren.toString()));
        if (Objects.equals(reservation.getHotelCode(), HotelType.ARAXA.getCode()))
            return String.format("%s%s%s?", URL_SITE_ARAXA_RESERVES, URL_SEARCH_ARAXA_RESERVES, reservation.getHotel().getCode())
                    .concat(String.join("&", parameters));
        return String.format("%s%s%s?", URL_SITE_TAUA_RESERVES, URL_SEARCH_TAUA_RESERVES, reservation.getHotel().getCode())
                .concat(String.join("&", parameters));
    }
}

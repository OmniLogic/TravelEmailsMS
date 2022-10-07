package ai.omnilogic.travel.emails.services.reservation;


import ai.omnilogic.travel.emails.dto.reservation.ReservationDTO;
import ai.omnilogic.travel.emails.models.mail.Mail;
import freemarker.template.TemplateException;
import org.springframework.messaging.MessagingException;

import java.io.IOException;

public interface SendingEmailReservationService {

    void sendRequestReservation(ReservationDTO reservation);

    void sendConfirmReserveMail(ReservationDTO reservation);

    /*void sendSaleConfirmMail(Mail mailModel);

    void sendSaleMail(Mail mailModel);*/
}

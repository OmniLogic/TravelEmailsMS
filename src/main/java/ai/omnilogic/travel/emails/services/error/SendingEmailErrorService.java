package ai.omnilogic.travel.emails.services.error;

import ai.omnilogic.travel.emails.dto.UserNotificationDTO;
import ai.omnilogic.travel.emails.dto.reservation.ReservationDTO;

public interface SendingEmailErrorService {

    void sendErrorPaymentCreditCardMail(ReservationDTO reservationDTO);

    void sendErrorPaymentPixMail(ReservationDTO reservationDTO);

    void sendNotificationErrorMail(UserNotificationDTO userNotificationDTO);
}

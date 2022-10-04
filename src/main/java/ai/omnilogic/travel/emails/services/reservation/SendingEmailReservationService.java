package ai.omnilogic.travel.emails.services.reservation;


import ai.omnilogic.travel.emails.dto.reservation.ReservationDTO;

public interface SendingEmailReservationService {

    void sendRequesteReservation(ReservationDTO reservation);

    void reSendConfirmReserveMail(ReservationDTO reservation, String login);
}

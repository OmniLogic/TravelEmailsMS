package ai.omnilogic.travel.emails.services.reservation;


import ai.omnilogic.travel.emails.dto.reservation.ReservationDTO;

public interface SendingEmailReservationService {

    void sendRequestReservation(ReservationDTO reservation);

    void sendConfirmReserveMail(ReservationDTO reservation);
}

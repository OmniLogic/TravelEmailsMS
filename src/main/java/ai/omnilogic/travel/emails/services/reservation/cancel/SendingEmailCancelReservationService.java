package ai.omnilogic.travel.emails.services.reservation.cancel;

import ai.omnilogic.travel.emails.dto.reservation.ReservationDTO;
import ai.omnilogic.travel.emails.models.hotel.HotelType;

public interface SendingEmailCancelReservationService {

    void sendCancelReserveMail(ReservationDTO reservation);

    void sendReserveCancellationRequest(ReservationDTO reservation);

    void sendReserveCancellationRequestTaua(ReservationDTO reservation);

    void sendToTauaReserveCancellationByClient(ReservationDTO reservation);
}

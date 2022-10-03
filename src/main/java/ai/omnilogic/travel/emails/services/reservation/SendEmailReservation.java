package ai.omnilogic.travel.emails.services.reservation;

public interface SendEmailReservation {

    void sendRequesteReservation(OrderReserve orderReserve);

    void reSendConfirmReserveMail(OrderReserve orderReserve, String login);
}

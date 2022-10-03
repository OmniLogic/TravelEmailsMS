package ai.omnilogic.travel.emails.controller;

import ai.omnilogic.travel.emails.services.reservation.SendEmailReservation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("sendmail")
public class SendMailController {

    private final SendEmailReservation sendEmailReservation;

    public SendMailController(SendEmailReservation sendEmailReservation) {
        this.sendEmailReservation = sendEmailReservation;
    }

    @PostMapping("/request_reservation")
    public void requestReservation(@RequestBody OrderReserve orderReserve) {
        sendEmailReservation.sendRequesteReservation(orderReserve);
    }
}

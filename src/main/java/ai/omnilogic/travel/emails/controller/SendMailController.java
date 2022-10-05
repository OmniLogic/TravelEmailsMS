package ai.omnilogic.travel.emails.controller;

import ai.omnilogic.travel.emails.dto.reservation.ReservationDTO;
import ai.omnilogic.travel.emails.services.reservation.SendingEmailReservationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("sendmail")
public class SendMailController {

    private final SendingEmailReservationService sendingEmailReservationService;

    public SendMailController(SendingEmailReservationService sendingEmailReservationService) {
        this.sendingEmailReservationService = sendingEmailReservationService;
    }


    @PostMapping("/request_reservation")
    public void requestReservation(@RequestBody ReservationDTO reservation) {
        sendingEmailReservationService.sendRequesteReservation(reservation);
    }
}

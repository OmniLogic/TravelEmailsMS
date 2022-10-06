package ai.omnilogic.travel.emails.controller;

import ai.omnilogic.travel.emails.dto.reservation.ReservationDTO;
import ai.omnilogic.travel.emails.services.reservation.SendingEmailReservationService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;


@RestController
@RequestMapping("sendmail")
public class SendMailController {

    private final SendingEmailReservationService sendingEmailReservationService;

    public SendMailController(SendingEmailReservationService sendingEmailReservationService) {
        this.sendingEmailReservationService = sendingEmailReservationService;
    }


    @PostMapping("/request_reservation")
    public void requestReservation(@RequestBody ReservationDTO reservation) {
        sendingEmailReservationService.sendRequestReservation(reservation);
    }

    @PostMapping("/confirm_reserve")
    public ResponseEntity saleMailByReserve(@RequestBody ReservationDTO reservation){

        try {
            sendingEmailReservationService.sendConfirmReserveMail(reservation);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body(ex.getMessage()) ;
        }

        return ResponseEntity.ok().build();
    }
}

package ai.omnilogic.travel.emails.controller;

import ai.omnilogic.travel.emails.dto.reservation.ReservationDTO;
import ai.omnilogic.travel.emails.dto.telesale.TelesaleDTO;
import ai.omnilogic.travel.emails.models.mail.Mail;
import ai.omnilogic.travel.emails.services.reservation.SendingEmailReservationService;
import ai.omnilogic.travel.emails.services.telesale.SendingEmailTelesaleService;
import freemarker.template.TemplateException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.io.IOException;


@RestController
@RequestMapping("sendmail")
public class SendMailController {

    private final SendingEmailReservationService sendingEmailReservationService;
    private final SendingEmailTelesaleService sendingEmailTelesaleService;

    public SendMailController(SendingEmailReservationService sendingEmailReservationService, SendingEmailTelesaleService sendingEmailTelesaleService) {
        this.sendingEmailReservationService = sendingEmailReservationService;
        this.sendingEmailTelesaleService = sendingEmailTelesaleService;
    }


    @PostMapping("/request_reservation")
    public ResponseEntity requestReservation(@RequestBody ReservationDTO reservation) {
        try {
            sendingEmailReservationService.sendRequestReservation(reservation);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        return ResponseEntity.ok().build();
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
    /*
    @Deprecated
    @PostMapping("/send_sale_confirm_mail")
    public ResponseEntity saleConfirm(@RequestBody Mail mail){

        try {
            sendingEmailReservationService.sendSaleConfirmMail(mail);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body(ex.getMessage()) ;
        }

        return ResponseEntity.ok().body("Send mail to: "+mail.getTo());
    }
    @Deprecated
    @PostMapping("/send_sale_mail")
    public ResponseEntity saleMail(@RequestBody Mail mail){

        try {
            sendingEmailReservationService.sendSaleMail(mail);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body(ex.getMessage()) ;
        }

        return ResponseEntity.ok().body("Send mail to: "+mail.getTo());
    }*/

    @PostMapping("/budget")
    public ResponseEntity sendBudget(@RequestBody TelesaleDTO telesale) {
        try {
            sendingEmailTelesaleService.sendBudgetMail(telesale);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/pre_sale")
    public ResponseEntity sendPreSale(@RequestBody TelesaleDTO telesale) {
        try {
            sendingEmailTelesaleService.sendPreSaleMail(telesale);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        return ResponseEntity.ok().build();
    }
}

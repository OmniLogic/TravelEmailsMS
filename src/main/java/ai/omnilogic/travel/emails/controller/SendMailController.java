package ai.omnilogic.travel.emails.controller;

import ai.omnilogic.travel.emails.dto.UserNotificationDTO;
import ai.omnilogic.travel.emails.dto.reservation.ReservationDTO;
import ai.omnilogic.travel.emails.dto.telesale.TelesaleDTO;
import ai.omnilogic.travel.emails.models.mail.Mail;
import ai.omnilogic.travel.emails.services.error.SendingEmailErrorService;
import ai.omnilogic.travel.emails.services.reservation.SendingEmailReservationService;
import ai.omnilogic.travel.emails.services.reservation.cancel.SendingEmailCancelReservationService;
import ai.omnilogic.travel.emails.services.sendmail.SendingEmailService;
import ai.omnilogic.travel.emails.services.telesale.SendingEmailTelesaleService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("sendmail")
public class SendMailController {

    private final SendingEmailReservationService sendingEmailReservationService;

    private final SendingEmailTelesaleService sendingEmailTelesaleService;

    private final SendingEmailErrorService sendingEmailErrorService;

    private final SendingEmailService sendingEmailService;

    private final SendingEmailCancelReservationService sendingEmailCancelReservationService;

    public SendMailController(SendingEmailReservationService sendingEmailReservationService, SendingEmailTelesaleService sendingEmailTelesaleService, SendingEmailErrorService sendingEmailErrorService, SendingEmailService sendingEmailService, SendingEmailCancelReservationService sendingEmailCancelReservationService) {
        this.sendingEmailReservationService = sendingEmailReservationService;
        this.sendingEmailTelesaleService = sendingEmailTelesaleService;
        this.sendingEmailErrorService = sendingEmailErrorService;
        this.sendingEmailService = sendingEmailService;
        this.sendingEmailCancelReservationService = sendingEmailCancelReservationService;
    }


    @PostMapping("/request-reservation")
    public ResponseEntity requestReservation(@RequestBody ReservationDTO reservation) {
        try {
            sendingEmailReservationService.sendRequestReservation(reservation);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/confirm-reserve")
    public ResponseEntity saleMailByReserve(@RequestBody ReservationDTO reservation){

        try {
            sendingEmailReservationService.sendConfirmReserveMail(reservation);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body(ex.getMessage()) ;
        }

        return ResponseEntity.ok().build();
    }

    @PostMapping("/budget")
    public ResponseEntity sendBudget(@RequestBody TelesaleDTO telesale) {
        try {
            sendingEmailTelesaleService.sendBudgetMail(telesale);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/pre-sale")
    public ResponseEntity sendPreSale(@RequestBody TelesaleDTO telesale) {
        try {
            sendingEmailTelesaleService.sendPreSaleMail(telesale);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/error-payment-credit-card")
    public ResponseEntity sendErrorPaymentCreditCardMail(@RequestBody ReservationDTO reservation) {
        try {
            sendingEmailErrorService.sendErrorPaymentCreditCardMail(reservation);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/error-payment-pix")
    public ResponseEntity sendErrorPaymentPixMail(@RequestBody ReservationDTO reservation) {
        try {
            sendingEmailErrorService.sendErrorPaymentPixMail(reservation);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/notification-error")
    public ResponseEntity sendNotificationErrorMail(@RequestBody UserNotificationDTO userNotificationDTO) {
        try {
            sendingEmailErrorService.sendNotificationErrorMail(userNotificationDTO);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/generic")
    public ResponseEntity sendGenericMail(@RequestBody Mail mail ){

        try {
            sendingEmailService.sendGenericMail(mail);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body(ex.getMessage()) ;
        }

        return ResponseEntity.ok().build();
    }

    @PostMapping("/cancellation-request-pix")
    public ResponseEntity sendCancellationRequestPixMail(@RequestBody ReservationDTO reservationDTO) {
        try {
            sendingEmailCancelReservationService.sendReserveCancellationRequest(reservationDTO);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/cancellation-request-taua")
    public ResponseEntity sendCancellationRequestTauaMail(@RequestBody ReservationDTO reservationDTO) {
        try {
            sendingEmailCancelReservationService.sendReserveCancellationRequestTaua(reservationDTO);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/to-taua-cancellation-by-client")
    public ResponseEntity sendToTauaReserveCancellationByClient(@RequestBody ReservationDTO reservationDTO) {
        try {
            sendingEmailCancelReservationService.sendToTauaReserveCancellationByClient(reservationDTO);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/cancel-reservation")
    public ResponseEntity sendCancelReserveMail(@RequestBody ReservationDTO reservationDTO) {
        try {
            sendingEmailCancelReservationService.sendCancelReserveMail(reservationDTO);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        return ResponseEntity.ok().build();
    }

}

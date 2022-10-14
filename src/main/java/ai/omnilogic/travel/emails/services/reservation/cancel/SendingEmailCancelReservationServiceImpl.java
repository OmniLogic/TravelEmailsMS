package ai.omnilogic.travel.emails.services.reservation.cancel;

import ai.omnilogic.travel.emails.dto.reservation.ItemReservationDTO;
import ai.omnilogic.travel.emails.dto.reservation.ReservationDTO;
import ai.omnilogic.travel.emails.dto.tariff.PricingTariffRuleDTO;
import ai.omnilogic.travel.emails.models.hotel.HotelType;
import ai.omnilogic.travel.emails.models.mail.Mail;
import ai.omnilogic.travel.emails.models.payment.PaymentMethod;
import ai.omnilogic.travel.emails.services.sendmail.SendingEmailService;
import ai.omnilogic.travel.emails.services.sendmail.SendingEmailServiceImpl;
import ai.omnilogic.travel.emails.utils.Utils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class SendingEmailCancelReservationServiceImpl implements SendingEmailCancelReservationService {

    private final SendingEmailService sendingEmailService;

    @Value("${taua.email.request-cancel}")
    private String TAUA_EMAIL_REQUEST_CANCEL;

    @Value("${taua.email.request-cancel-araxa}")
    private String TAUA_EMAIL_REQUEST_CANCEL_ARAXA;

    public SendingEmailCancelReservationServiceImpl(SendingEmailService sendingEmailService) {
        this.sendingEmailService = sendingEmailService;
    }

    /**
     * @param reservation
     */
    @Override
    public void sendCancelReserveMail(ReservationDTO reservation) {
        try {
            Mail cancelMail = cancelMailBuilder(reservation);
            if (Objects.equals(reservation.getHotelCode(), HotelType.ARAXA.getCode()))
                sendingEmailService.sendMail(cancelMail, "emailCancelConfirmAraxa.ftl");
            else
                sendingEmailService.sendMail(cancelMail, "emailCancelConfirm.ftl");
        } catch (Exception e) {
            throw new MessagingException("Erro to send mail of request cancellation:" + e.getMessage());
        }
    }

    /**
     * @param reservation
     */
    @Override
    public void sendReserveCancellationRequest(ReservationDTO reservation) {
        try {
            Mail email = createDataToSendReserveCancellation(reservation);
            if (Objects.equals(reservation.getHotelCode(), HotelType.ARAXA.getCode()))
                sendingEmailService.sendMail(email, "emailCancelConfirmAraxa.ftl");
            else
                sendingEmailService.sendMail(email, "emailCancelConfirm.ftl");
        } catch (Exception e) {
            throw new MessagingException("Erro to send mail of request cancellation:" + e.getMessage());
        }
    }

    /**
     * @param reservation
     */
    @Override
    public void sendReserveCancellationRequestTaua(ReservationDTO reservation) {
        try {

            Mail email = createDataToSendReserveCancellationTaua(reservation);
            if (Objects.equals(reservation.getHotelCode(), HotelType.ARAXA.getCode()))
                sendingEmailService.sendMail(email, "emailCancelConfirmAraxa.ftl");
            else
                sendingEmailService.sendMail(email, "emailCancelConfirm.ftl");
        } catch (Exception e) {
            throw new MessagingException("Error to send mail confirm cancel:" + e.getMessage());
        }
    }

    /**
     * @param reservation
     */
    @Override
    public void sendToTauaReserveCancellationByClient(ReservationDTO reservation) {
        try {
            Mail email = createDataSendToTauaReserveCancellationByClient(reservation);
            if (reservation.getHotelCode() == 3)
                sendingEmailService.sendMail(email, "emailToTauaCancelByClientAraxa.ftl");
            else
                sendingEmailService.sendMail(email, "emailToTauaCancelByClient.ftl");
        } catch (Exception ex) {
            throw new MessagingException("Error to send mail confirm cancel:" + ex.getMessage());
        }
    }

    private Mail createDataSendToTauaReserveCancellationByClient(ReservationDTO reservation) {
        Mail email = new Mail();
        HotelType hotelType = HotelType.getNameByCode(reservation.getHotelCode());
        SendingEmailServiceImpl.defineAraxaOrNo(email, reservation.getHotelCode());
        email.setSubject(String.format("#%s - %s", reservation.getReserveId(), hotelType.getName()));
        email.setTo(TAUA_EMAIL_REQUEST_CANCEL);
        Map<String, Object> model = new HashMap<>();
        String message = String.format("Solicitação de cancelamento:<br>" +
                "O cliente da reserva %s para %s realizou cancelamento.", reservation.getReserveId(), hotelType.getName());
        model.put("message", message);
        model.put("hotelImage", Utils.imgHotel(hotelType));
        email.setModel(model);
        return email;
    }

    private Mail createDataToSendReserveCancellation(ReservationDTO reservation) throws Exception {

        Mail email = new Mail();
        SendingEmailServiceImpl.defineAraxaOrNo(email, reservation.getHotelCode());
        Map<String, Object> model = new HashMap();

        try {
            email.setSubject(String.format("Solicitação de Cancelamento #%s", reservation.getReserveId()));
            email.setTo(reservation.getCustomer().getEmail());

            model.put("hotelImage", Utils.imgHotel(reservation.getHotel().getType()));
            model.put("title", String.format("Sua solicitação de cancelamento da reserva %s no %s está sendo processada.",
                    reservation.getReserveId(),
                    HotelType.getNameByCode(reservation.getHotel().getCode()).getDescription()));
            model.put("content", "<hr /><br />");
            model.put("inf_refound", "<b>Informações sobre reembolso:<b/><br />");

            List<Map<String, String>> listTariffs = new ArrayList<>();

            List<PricingTariffRuleDTO> tariffs = reservation.getItems().stream().map(ItemReservationDTO::getTariff).toList();

            String hour = reservation.getHourCheckInHotel();
            //Horas corridas entre a hora atual e hora de check in
            long hours = ChronoUnit.HOURS.between(LocalDateTime.parse(reservation.getCancellationRequestDate()),
                    LocalDateTime.parse(reservation.getHotel().getStayDateStart() + "T" + hour + ":00.000"));

            String key = "description";
            AtomicReference<String> value = new AtomicReference<>("");
            reservation.getItems().forEach(itemRoom -> {
                tariffs.forEach(tariff -> {
                    if (itemRoom.getTariffId().equals(tariff.getId()) &&
                            ((tariff.getRefundable().getRefundable() && hours >= tariff.getRefundable().getRefundLimitHours()) ||
                                    Utils.nightsStays(reservation.getDate(), reservation.getCancellationRequestDate()) <= 7)) {
                        String dateCheckin = reservation.getHotel().getStayDateStart() + "T" +
                                hour +
                                ":00.000";
                        DateTimeFormatter dtf4 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                        String dateCancel = dtf4.format(LocalDateTime.parse(dateCheckin).minusHours(tariff.getRefundable().getRefundLimitHours())).toString();

                        if (Utils.nightsStays(reservation.getDate(), reservation.getCancellationRequestDate()) <= 7)
                            value.set(itemRoom.getDescription() + ", " + tariff.getDisplayPrice().getLabelPublic() + "! " +
                                    "O cancelamento foi solicitado no prazo de 7 dias, portanto será realizado o estorno do valor pago!");
                        else
                            value.set(String.format("%s<br />%s<br /><i>A tarifa permitia cancelamento até %s. Como você cancelou dentro do prazo, " +
                                            "o estorno será realizado de acordo com as políticas aplicadas junto ao hotel.<i/><br /><br />",
                                    itemRoom.getDescription(),
                                    tariff.getDisplayPrice().getLabelPublic(),
                                    dateCancel));
                        listTariffs.add(Map.of(key, value.get()));
                    } else if (itemRoom.getTariffId().equals(tariff.getId())) {
                        value.set(String.format("%s<br />%s<br />" +
                                        "<i>A sua tarifa é não-reembolsável. De acordo com as políticas aplicadas junto ao hotel não " +
                                        "contempla estorno do valor pago.<i/><br /><br />",
                                itemRoom.getDescription(),
                                tariff.getDisplayPrice().getLabelPublic()));
                        listTariffs.add(Map.of(key, value.get()));
                    }
                });
            });

            value.set(String.format("<hr /><br />Aguarde a confirmação do cancelamento no seu e-mail.\n" +
                            "Esperamos ver você de novo por aqui.<br /><br />" +
                            "Atenciosamente, <i>%s<i/>",
                    HotelType.getNameByCode(reservation.getHotel().getCode()).getDescription()));
            listTariffs.add(Map.of(key, value.get()));

            model.put("tariffs_descrition", listTariffs);
        } catch (Exception e) {
            throw new Exception("Erro on create model of request cancelation" + e.getMessage());
        }

        email.setModel(model);

        return email;
    }

    private Mail createDataToSendReserveCancellationTaua(ReservationDTO reservation) throws Exception {

        Mail email = new Mail();
        SendingEmailServiceImpl.defineAraxaOrNo(email, reservation.getHotelCode());
        Map<String, Object> model = new HashMap();

        try {
            email.setSubject(String.format("%s %s", reservation.getReserveId(), reservation.getItems().get(0)
                    .getDescription()));
            email.setTo(reservation.getHotelCode() == 3 ? TAUA_EMAIL_REQUEST_CANCEL_ARAXA : TAUA_EMAIL_REQUEST_CANCEL);

            model.put("hotelImage", Utils.imgHotel(reservation.getHotel().getType()));
            model.put("title", String.format("Solicitação de cancelamento: <br>O cliente da reserva %s para %s solicita cancelamento.",
                    reservation.getReserveId(),
                    HotelType.getNameByCode(reservation.getHotel().getCode()).getDescription()));
            model.put("content", "<hr /><br />");
            model.put("inf_refound", "<b>Informações sobre reembolso:<b/><br />");

            List<Map<String, String>> listTariffs = new ArrayList<>();

            List<PricingTariffRuleDTO> tariffs = reservation.getItems().stream().map(ItemReservationDTO::getTariff).toList();

            String key = "description";
            AtomicReference<String> value = new AtomicReference<>("");
            AtomicReference<Integer> refoundAmount = new AtomicReference<>(0);
            Set<String> roomsCancel = new HashSet<>();
            AtomicBoolean refundable = new AtomicBoolean(false);

            String hour = reservation.getHourCheckInHotel();
            //Horas corridas entre a hora atual e hora de check in
            long hours = ChronoUnit.HOURS.between(LocalDateTime.parse(reservation.getCancellationRequestDate()),
                    LocalDateTime.parse(reservation.getHotel().getStayDateStart() + "T" + hour + ":00.000"));

            reservation.getItems().forEach(itemRoom -> {
                tariffs.forEach(tariff -> {
                    if (itemRoom.getTariffId().equals(tariff.getId()) &&
                            ((tariff.getRefundable().getRefundable() && hours >= tariff.getRefundable().getRefundLimitHours()) ||
                                    Utils.nightsStays(reservation.getDate(), reservation.getCancellationRequestDate()) <= 7)) {
                        refoundAmount.set(refoundAmount.get() + (itemRoom.getAmountAfterTax()));
                        refundable.set(true);

                        roomsCancel.add("<br>" + itemRoom.getCode() + "<br>" +
                                itemRoom.getDescription() + " - " + tariff.getDisplayPrice().getLabelPublic() +
                                " - " + Utils.currencyFormatCentsToReal(BigDecimal.valueOf(itemRoom.getAmountAfterTax())));
                    } else if (itemRoom.getTariffId().equals(tariff.getId())) {
                        value.set(String.format("%s<br />%s<br />" +
                                        "<i>A sua tarifa é não-reembolsável.<i/><br /><br />",
                                itemRoom.getDescription(),
                                tariff.getDisplayPrice().getLabelPublic()));
                        listTariffs.add(Map.of(key, value.get()));
                    }
                });
            });

            if (reservation.getItemAditionalServices() != null &&
                    reservation.getItemAditionalServices().size() > 0 && refoundAmount.get() > 0)
                reservation.getItemAditionalServices().stream().forEach(itemAdditional -> {
                    refoundAmount.set(refoundAmount.get() + (itemAdditional.getAmountAfterTax()));
                });

            String infoRefound, valueRefound = null;
            AtomicReference<String> responseMessage = new AtomicReference<>(" ");
            roomsCancel.forEach(message -> {
                responseMessage.set(responseMessage.get() + message);
            });

            if (reservation.getPayments().stream().anyMatch(payment -> (payment.getPaymentMethod().equals(PaymentMethod.PIX)))) {

                if (refundable.get()) {
                    valueRefound = Utils.currencyFormatCentsToReal(BigDecimal.valueOf(refoundAmount.get()));
                    infoRefound = "Após seguir o passo a passo acima, para as reservas com tarifas flexíveis com direito a reembolso deverá " +
                            "ser solicitado ao cliente os dados bancários para a realização do depósito de reembolso. Após o recebimento " +
                            "dos dados, enviar para o financeiro efetuar a transação.";
                    responseMessage.set(responseMessage.get().concat(String.format("<br>Valor total a ser reembolsado: %s<br/>", valueRefound)));
                } else {
                    infoRefound = "<br>";
                }
            } else {
                infoRefound = "Após seguir o passo a passo acima, as reservas com tarifas flexíveis com direito ao" +
                        " reembolso serão estornadas automaticamente para o cliente e ele receberá um e-mail de confirmação.";
            }


            value.set(String.format("%s <br><i> Próximos passos:<i/><br> " +
                            "1- Entre no sistema Omnilogic na área de reservas; <br>" +
                            "2- Busque a reserva e abra o detalhamento; <br>" +
                            "3- Clique na opção ‘cancelar’; <br>" +
                            "4- Entre no sistema Volux e acesse a reserva; <br>" +
                            "5- Cancele a reserva;<br> " +
                            "%s",
                    responseMessage, infoRefound));


            listTariffs.add(Map.of(key, value.get()));

            model.put("tariffs_descrition", listTariffs);
        } catch (Exception e) {
            throw new Exception("Error to create model of mail taua notification on cancel");
        }

        email.setModel(model);

        return email;
    }

    private Mail cancelMailBuilder(ReservationDTO reservation) {
        Mail mail = new Mail();
        SendingEmailServiceImpl.defineAraxaOrNo(mail, reservation.getHotelCode());
        mail.setSubject("Sua reserva foi cancelada.");
        mail.setTo(reservation.getCustomer().getEmail());
        mail.setCc(reservation.getEmailCc());
        mail.setHotelCode(reservation.getHotel().getCode());
        mail.setReserveId(reservation.getReserveId());
        Map model = new HashMap();

        model.put("hotelImage", Utils.imgHotel(reservation.getHotel().getType()));

        model.put("title", "Sua reserva foi cancelada\n");
        model.put("content", "Já cancelamos sua reserva "+reservation.getReserveId()+" no "+HotelType.getNameByCode(reservation.getHotel().getCode()).getName()+ ", conforme solicitado.\n");
        model.put("inf_refound", "Informações sobre reembolso:\n");

        List<Map<String, Object>> listTarifs = new ArrayList<Map<String, Object>>();
        List<PricingTariffRuleDTO> tariffs = reservation.getItems().stream().map(ItemReservationDTO::getTariff).toList();


        String hour = reservation.getHourCheckInHotel();
        //Horas corridas entre a hora atual e hora de check in
        long hours = ChronoUnit.HOURS.between(LocalDateTime.parse(reservation.getCancellationRequestDate()),
                LocalDateTime.parse(reservation.getHotel().getStayDateStart() + "T" + hour + ":00.000"));
        AtomicBoolean refundable = new AtomicBoolean(false);


        reservation.getItems().stream().forEach(item->{
            tariffs.stream().forEach(tariff->{
                Map<String, Object> tariff_itens = new HashMap<>();
                Objects.requireNonNull(tariff.getRefundable(), "E-mail não enviado. Tarifa sem configuração de estorno.");
                if(item.getTariffId().equals(tariff.getId()) &&
                        ((tariff.getRefundable().getRefundable() && hours >= tariff.getRefundable().getRefundLimitHours()) ||
                                Utils.nightsStays(reservation.getDate(), reservation.getCancellationRequestDate()) <= 7)){

                    refundable.set(true);
                    String dateCheckin = reservation.getHotel().getStayDateStart()+"T"+
                            hour +
                            ":00.000";
                    DateTimeFormatter dtf4 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                    //Integer limitHour = tariff.getPayment().getRefundLimitHours() !=null?tariff.getPayment().getRefundLimitHours():72;
                    String dateCancel = dtf4.format(LocalDateTime.parse(dateCheckin).minusHours(tariff.getRefundable().getRefundLimitHours())).toString();
                    if (Utils.nightsStays(reservation.getDate(), reservation.getCancellationRequestDate()) <= 7)
                        tariff_itens.put("description", item.getDescription()+", "+tariff.getDisplayPrice().getLabelPublic() + "! " +
                                "O cancelamento foi solicitado no prazo de 7 dias, portanto será realizado o estorno do valor pago!");
                    else
                        tariff_itens.put("description",item.getDescription()+", "+tariff.getDisplayPrice().getLabelPublic() + "! " +
                                "A tarifa permitia cancelamento até "+dateCancel+". Como você cancelou dentro do prazo, " +
                                "o estorno será realizado de acordo com as políticas aplicadas junto ao hotel.");
                    listTarifs.add(tariff_itens);
                }else if(item.getTariffId().equals(tariff.getId())){
                    tariff_itens.put("description", item.getDescription()+", "+tariff.getDisplayPrice().getLabelPublic() +
                            " A sua tarifa é não-reembolsável. De acordo as políticas aplicadas junto ao hotel, não contempla estorno do valor pago.\n" +
                            "Esperamos ver você de novo por aqui.");
                    listTarifs.add(tariff_itens);
                }
            });
        });

        if (refundable.get() &&
                reservation.getPayments().stream().anyMatch(payment -> payment.getPaymentMethod().getName().equals(PaymentMethod.PIX.getName()))) {
            Map<String, Object> tariff_itens = new HashMap<>();
            tariff_itens.put("description", "<b>ATENÇÃO!</b> Para receber o estorno dos valores que têm direito, gentileza entrar em contato com o nosso atendimento e informar os dados bancários para o depósito.");
            listTarifs.add(tariff_itens);
        }

        List<Map<String, Object>> tarifsList = new ArrayList<>();
        tarifsList.addAll(listTarifs);
        model.put("tariffs_descrition", tarifsList);
        mail.setModel(model);
        return mail;
    }

}

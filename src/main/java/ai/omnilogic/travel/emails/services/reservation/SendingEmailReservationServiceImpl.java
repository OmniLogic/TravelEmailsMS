package ai.omnilogic.travel.emails.services.reservation;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import ai.omnilogic.travel.emails.dto.additional_service.AdditionalServiceDTO;
import ai.omnilogic.travel.emails.dto.tariff.PricingTariffRuleDTO;
import ai.omnilogic.travel.emails.models.Mail;
import ai.omnilogic.travel.emails.models.hotel.HotelType;
import ai.omnilogic.travel.emails.dto.reservation.ItemReservationDTO;
import ai.omnilogic.travel.emails.dto.reservation.ReservationDTO;
import ai.omnilogic.travel.emails.services.sendmail.SendingEmailService;
import ai.omnilogic.travel.emails.utils.Utils;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SendingEmailReservationServiceImpl implements SendingEmailReservationService {

    @Value("${taua.email.from}")
    private String TAUA_EMAIL_FROM;

    @Value("${taua.email.from-araxa}")
    private String TAUA_EMAIL_FROM_ARAXA;

    @Value("${taua.email.request-cancel}")
    private String TAUA_EMAIL_REQUEST_CANCEL;

    @Value("${taua.email.confirm-reservation}")
    private  String TAUA_EMAIL_CONFIRM_RESERVATION;

    @Value("${taua.email.replay-to}")
    private String TAUA_EMAIL_REPLAYTO;

    @Value("${taua.email.replay-to-araxa}")
    private String TAUA_EMAIL_REPLAYTO_ARAXA;

    @Value("${taua.url.site}")
    private String URL_SITE_TAUA_RESERVES;

    @Value("${taua.url.site-araxa}")
    private String URL_SITE_ARAXA_RESERVES;

    @Value("${taua.url.search}")
    private String URL_SEARCH_TAUA_RESERVES;

    @Value("${taua.url.search-araxa}")
    private String URL_SEARCH_ARAXA_RESERVES;

    @Value("${taua.url.telesale}")
    private String URL_TELESALE_TAUA;

    private final SendingEmailService sendingEmailService;

    public SendingEmailReservationServiceImpl(SendingEmailService sendingEmailService) {
        this.sendingEmailService = sendingEmailService;
    }

    @Override
    public void sendRequesteReservation(ReservationDTO reservation) {
        Mail email = createDataToSendByReservation(reservation, "Pedido Recebido!", false);
        try {
            if (Objects.equals(reservation.getHotelCode(), HotelType.ARAXA.getCode())) {
                    sendingEmailService.sendMail(email, "emailOrderReserveAraxa.ftl");
            }
            else
                sendingEmailService.sendMail(email, "emailOrderReserve.ftl");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void reSendConfirmReserveMail(ReservationDTO reservation, String login) {

    }

    private Mail createDataToSendByReservation(ReservationDTO reservation, String title, boolean paid) {
        Mail email = new Mail();
        defineAraxaOrNo(email, reservation.getHotelCode());
        email.setHotelCode(reservation.getHotelCode());
        email.setReserveId(reservation.getReserveId());
        email.setTo(reservation.getCustomer().getEmail());
        email.setName(reservation.getCustomer().getFullName());
        email.setSubject(title);
        String number = "(31) 3236-1900";

        if(Optional.ofNullable(reservation.getTeleSaleId()).isPresent() && !reservation.getTeleSaleId().trim().isEmpty()){

            if(reservation.getIsTelesale() && reservation.getEmailSeller() != null
                    && !reservation.getEmailSeller().isEmpty()){
               email.setCc(reservation.getEmailSeller());
                if(paid)
                    number = "0800 333 1900";
            }
        }
        if(!Optional.ofNullable(email.getCc()).isPresent() || email.getCc().trim().isEmpty()){
            email.setBcc(TAUA_EMAIL_CONFIRM_RESERVATION);
        }

        Map<String, Object> model = new HashMap<>();
        List<Map<String, Object>> listOfPolicies = new ArrayList<>();

        if (reservation.getItems() != null && reservation.getItems().size() > 0
                && reservation.getItems().get(0).getTariffId() != null) {
            List<PricingTariffRuleDTO> tariffs = reservation.getItems().stream().map(itemOrder -> (itemOrder.getTariff())).collect(Collectors.toList());

            tariffs.stream().forEach(tariff->{
                Map<String, Object> tariffPolicies = new HashMap<>();
                tariffPolicies.put("tarife_title", tariff.getDisplayPrice().getLabelPublic());
                if(tariff.getDisplayPrice().getPoliciesRestrictions() != null){
                    tariffPolicies.put("tarife_policie", tariff.getDisplayPrice().getPoliciesRestrictions());
                }else{
                    tariffPolicies.put("tarife_policie", " ");
                }

                if(tariff.getDisplayPrice().getDescription() != null){
                    tariffPolicies.put("tarife_description", tariff.getDisplayPrice().getDescription());
                }else{
                    tariffPolicies.put("tarife_description", " ");
                }
                listOfPolicies.add(tariffPolicies);
            });
        }

        model.put("listPolicies", listOfPolicies);
        model.put("name", reservation.getCustomer().getFullName());
        model.put("reserve_id", reservation.getReserveId());
        //TODO domingo tem horario de checkin as 17:00
        model.put("checkinDate", Utils.formatDateToBr(reservation.getHotel().getStayDateStart()));
        model.put("checkinHour", "Check-in segunda a sábado: 15:00h </br> e aos domingos: 17:00h");
        model.put("number", number);
        String childText;
        if (reservation.getChildrenInformation() != null && !reservation.getChildrenInformation().isEmpty())
            childText = reservation.getChildrenInformation();
        else
            childText = "02 Crianças de 0 a 12 anos se hospedam gratuitamente quando ocupam o quarto dos pais ou guardiões, usando as camas existentes. Os pais devem apresentar a certidão de nascimento e documentação da criança. Animais de estimação não são aceitos.";

        model.put("childText", childText);


        //TODO domingo tem horario de checkin as 17:00
        model.put("checkoutDate", Utils.formatDateToBr(reservation.getHotel().getStayDateEnd()));
        model.put("checkoutHour", "12:00");
        model.put("codReserve", reservation.getReserveId());
        model.put("hotelName", HotelType.getNameByCode(reservation.getHotel().getCode()).getName());
        model.put("hotelImage", Utils.imgHotel(reservation.getHotel().getType()));
        List<Map<String, Object>> listOfLists = new ArrayList<Map<String, Object>>();

        for (ItemReservationDTO itemReserv : reservation.getItems()) {
            Map<String, Object> list_array = new HashMap<String, Object>();

            if (itemReserv.getTariff().getDisplayPrice().getLabelPublic() != null &&
                    !itemReserv.getTariff().getDisplayPrice().getLabelPublic().isEmpty()) {
                list_array.put("tariff_name", itemReserv.getTariff().getDisplayPrice().getLabelPublic());
            } else {
                list_array.put("tariff_name", "");
            }

            list_array.put("reserv", itemReserv.getDescription());
            list_array.put("reservResumeDescriptionItem", itemReserv.getDescription());
            list_array.put("reservResumeAmountItem", Utils.formatDecimal((new BigDecimal(itemReserv.getAmountBeforeTax()).divide(new BigDecimal(100)).setScale(2))));
            list_array.put("reservResumeAmount", Utils.formatDecimal((new BigDecimal(itemReserv.getAmount()).divide(new BigDecimal(100)).setScale(2))));

            listOfLists.add(list_array);
        }

        if (reservation.getItemAditionalServices() != null &&
                reservation.getItemAditionalServices().size() > 0) {
            for (AdditionalServiceDTO itemReserv : reservation.getItemAditionalServices()) {
                Map<String, Object> list_array = new HashMap<String, Object>();
                list_array.put("reserv", itemReserv.getDescription());
                list_array.put("reservResumeDescriptionItem", itemReserv.getDescription());
                list_array.put("reservResumeAmountItem", Utils.formatDecimal((new BigDecimal(itemReserv.getAmountAfterTax()).divide(new BigDecimal(100)).setScale(2))));
                list_array.put("reservResumeAmount", Utils.formatDecimal((new BigDecimal(itemReserv.getAmountAfterTax()).divide(new BigDecimal(100)).setScale(2))));
                listOfLists.add(list_array);
            }
        }
        model.put("listItens", listOfLists);

        int resumeAmountTotal = reservation.getTotals().getIss();
        model.put("reservResumeValTax", Utils.formatDecimal((new BigDecimal(resumeAmountTotal).divide(new BigDecimal(100)).setScale(2))));

        int resumeAmount = reservation.getTotals().getAmount();
        model.put("reservResumeAmount", Utils.formatDecimal((new BigDecimal(resumeAmount).divide(new BigDecimal(100)).setScale(2))));

        int roon = reservation.getItems().stream().mapToInt(ItemReservationDTO::getQuantity).filter(n -> n > 0).sum();
        model.put("roon", roon);
        int adult = reservation.getItems().stream().mapToInt(ItemReservationDTO::getAdults).filter(n -> n > 0).sum();
        model.put("adult", adult);
        int childreans = reservation.getItems().stream().mapToInt(ItemReservationDTO::getChildrens).filter(n -> n > 0).sum();
        model.put("childreans", childreans);

        int amount = reservation.getTotals().getAmount();
        model.put("vlPaymant", Utils.formatDecimal((new BigDecimal(amount).divide(new BigDecimal(100)).setScale(2))));
        model.put("total", Utils.formatDecimal((new BigDecimal(amount).divide(new BigDecimal(100)).setScale(2))));

        email.setModel(model);

        return email;
    }

    private void defineAraxaOrNo(Mail mail, Integer hotelCode) {
        if (hotelCode.equals(HotelType.ARAXA.getCode())) {
            mail.setFrom(TAUA_EMAIL_FROM_ARAXA);
            mail.setReplayTo(TAUA_EMAIL_REPLAYTO_ARAXA);
        } else {
            mail.setFrom(TAUA_EMAIL_FROM);
            mail.setReplayTo(TAUA_EMAIL_REPLAYTO);
        }
    }
}

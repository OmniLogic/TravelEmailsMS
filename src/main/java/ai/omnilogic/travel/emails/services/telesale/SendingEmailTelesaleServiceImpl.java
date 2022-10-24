package ai.omnilogic.travel.emails.services.telesale;

import ai.omnilogic.travel.emails.dto.reservation.ItemReservationDTO;
import ai.omnilogic.travel.emails.dto.tariff.PricingTariffRuleDTO;
import ai.omnilogic.travel.emails.dto.telesale.OptionDTO;
import ai.omnilogic.travel.emails.dto.telesale.TelesaleDTO;
import ai.omnilogic.travel.emails.enums.AgeGroup;
import ai.omnilogic.travel.emails.models.hotel.HotelEmail;
import ai.omnilogic.travel.emails.models.hotel.HotelType;
import ai.omnilogic.travel.emails.models.mail.Mail;
import ai.omnilogic.travel.emails.services.sendmail.SendingEmailService;
import ai.omnilogic.travel.emails.services.sendmail.SendingEmailServiceImpl;
import ai.omnilogic.travel.emails.utils.Utils;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SendingEmailTelesaleServiceImpl implements SendingEmailTelesaleService {

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

    public SendingEmailTelesaleServiceImpl(SendingEmailService sendingEmailService) {
        this.sendingEmailService = sendingEmailService;
    }


    @Override
    public void sendBudgetMail(TelesaleDTO telesale) {

        Mail mailModel = this.budGetMailBuilder(telesale);

        try {
            if (Objects.equals(telesale.getHotelCode(), HotelType.ARAXA.getCode()))
                sendingEmailService.sendMail(mailModel, "emailBudgetAraxa.ftl");
            else
                sendingEmailService.sendMail(mailModel, "emailBudget.ftl");
        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void sendPreSaleMail(TelesaleDTO telesale) {
        Mail mailModel = this.preSaleMailBuilder(telesale);

        try {
            if (Objects.equals(telesale.getHotelCode(), HotelType.ARAXA.getCode()))
                sendingEmailService.sendMail(mailModel, "mailPreSaleAraxa.ftl");
            else
                sendingEmailService.sendMail(mailModel, "mailPreSale.ftl");
        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
    }

    private Mail budGetMailBuilder(TelesaleDTO telesale) {
        Mail email = new Mail();
        HotelEmail hotelEmail = telesale.getEmail().getEmail();
        sendingEmailService.defineAraxaOrNo(email, telesale.getHotelCode());
        email.setHotelCode(telesale.getHotelCode());
        email.setReserveId(telesale.getSaleId());
        email.setTo(telesale.getCustomer().getEmail());
        email.setName(telesale.getCustomer().getFullName());
        email.setSubject(hotelEmail.getSubject());
        email.setReplayTo(telesale.getEmailSeller());
        String cc = telesale.getEmailSeller();
        if(!hotelEmail.getCc().isEmpty())
            cc = String.format("%s,%s", cc, String.join(",", hotelEmail.getCc()));
        email.setCc(cc);

        Map<String, Object> model = new HashMap();
        model.put("name", telesale.getCustomer().getFullName());
        model.put("hotelName", telesale.getHotelName());
        model.put("city", telesale.getCityHotel());
        model.put("checkinHour", "17:00");
        model.put("checkinDate", Utils.formatDateToBr(telesale.getHotel().getStayDateStart()));
        model.put("hotelImage", Utils.imgHotel(telesale.getHotel().getType()));
        model.put("checkoutHour", "12:00");
        try {
            model.put("checkoutDate", Utils.formatDateToBr(telesale.getHotel().getStayDateEnd()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        model.put("name", telesale.getCustomer().getFullName());
        model.put("listOptions", listOptionsTeleSale(telesale));

        int qtdAdults = telesale.getOptions().get(0).getItems().stream().mapToInt(ItemReservationDTO::getAdults).filter(n -> n > 0).sum();
        model.put("adults", qtdAdults);
        int qtdchildrens = telesale.getOptions().get(0).getItems().stream().mapToInt(ItemReservationDTO::getChildrens).filter(n -> n > 0).sum();
        model.put("childrens", qtdchildrens);

        model.put("stayDateStart", Utils.formatDateToBr(telesale.getHotel().getStayDateStart()));
        model.put("stayDateEnd", Utils.formatDateToBr(telesale.getHotel().getStayDateEnd()));

        model.put("minhaurl", makeLinkBudget(telesale, telesale.getOptions().get(0), qtdAdults, qtdchildrens));

        model.put("document", telesale.getCustomer().getDocument());
        model.put("hotel_code", telesale.getHotel().getCode());

        model.put("token", telesale.getToken());

        model.put("number", hotelEmail.getContactPhone());
        model.put("email", hotelEmail.getContatEmail());
        model.put("policies", telesale.getEmail().getTermPoliciesHtml());

        String childText;

        if (telesale.getChildrenInformation() != null && !telesale.getChildrenInformation().trim().isEmpty())
            childText = telesale.getChildrenInformation();
        else
            childText = "02 Crianças de 0 a 12 anos se hospedam gratuitamente quando ocupam o quarto dos pais ou guardiões, usando as camas existentes. Os pais devem apresentar a certidão de nascimento e documentação da criança. Animais de estimação não são aceitos.";

        model.put("childText", childText);

        makeTariffDescription(telesale, model);

        email.setModel(model);

        return email;
    }

    private Mail preSaleMailBuilder(TelesaleDTO telesale) {
        Mail email = new Mail();
        HotelEmail hotelEmail = telesale.getEmail().getEmail();
        sendingEmailService.defineAraxaOrNo(email, telesale.getHotelCode());
        email.setHotelCode(telesale.getHotelCode());
        email.setReserveId(telesale.getSaleId());
        email.setTo(telesale.getCustomer().getEmail());
        email.setName(telesale.getCustomer().getFullName());
        email.setSubject(hotelEmail.getSubject());
        email.setReplayTo(telesale.getEmailSeller());
        String cc = telesale.getEmailSeller();
        if(!hotelEmail.getCc().isEmpty())
            cc = String.format("%s,%s", cc, String.join(",", hotelEmail.getCc()));
        email.setCc(cc);

        Map<String, Object> model = new HashMap();
        model.put("name", telesale.getCustomer().getFullName());
        model.put("hotelName", telesale.getHotelName());
        model.put("city", telesale.getCityHotel());
        model.put("checkinHour", "17:00");
        model.put("code", telesale.getSaleId());
        model.put("hotelImage", Utils.imgHotel(telesale.getHotel().getType()));
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime now = LocalDateTime.parse(telesale.getDate()).plusHours(telesale.getValidityInHours());
        model.put("dateHour", dtf.format(now));
        model.put("checkinDate", Utils.formatDateToBr(telesale.getHotel().getStayDateStart()));

        String childText;
        if (telesale.getChildrenInformation() != null && !telesale.getChildrenInformation().trim().isEmpty())
            childText = telesale.getChildrenInformation();
        else
            childText = "02 Crianças de 0 a 12 anos se hospedam gratuitamente quando ocupam o quarto dos pais ou guardiões, usando as camas existentes. Os pais devem apresentar a certidão de nascimento e documentação da criança. Animais de estimação não são aceitos.";

        model.put("childText", childText);

        model.put("number", hotelEmail.getContactPhone());
        model.put("email", hotelEmail.getContatEmail());
        model.put("policies", telesale.getEmail().getTermPoliciesHtml());

        makeTariffDescription(telesale, model);

        if (telesale.getOptions().get(0).getItems() != null && telesale.getOptions().get(0).getItems().size() > 0) {

            PricingTariffRuleDTO pricingRule = telesale.getOptions().stream().findFirst().get().getItems().stream().findFirst().get().getTariff();

            if (Optional.ofNullable(pricingRule).isPresent()) {
                pricingRule.getDisplayPrice().setPoliciesRestrictions(" ");
            }
            model.put("tariff_name", pricingRule.getDisplayPrice().getLabelPublic());
            model.put("tariff_description", pricingRule.getDisplayPrice().getDescription() != null?
                    pricingRule.getDisplayPrice().getDescription() : "");
            model.put("policies", pricingRule.getDisplayPrice().getPoliciesRestrictions());
        }

        model.put("checkoutHour", "12:00");
        try {
            model.put("checkoutDate", Utils.formatDateToBr(telesale.getHotel().getStayDateEnd()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        model.put("name", telesale.getCustomer().getFullName());
        int qtdAdults = telesale.getOptions().get(0).getItems().stream().mapToInt(ItemReservationDTO::getAdults).filter(n -> n > 0).sum();
        model.put("adults", qtdAdults);
        int qtdchildrens = telesale.getOptions().get(0).getItems().stream().mapToInt(ItemReservationDTO::getChildrens).filter(n -> n > 0).sum();
        model.put("childrens", qtdchildrens);

        model.put("listItens", listIensTeleSale(telesale.getOptions().get(0).getItems()));
        model.put("stayDateStart", Utils.formatDateToBr(telesale.getHotel().getStayDateStart()));
        model.put("stayDateEnd", Utils.formatDateToBr(telesale.getHotel().getStayDateEnd()));

        int resumeAmount = telesale.getOptions().get(0).getItems().stream().mapToInt(ItemReservationDTO::getAmountBeforeTax).filter(n -> n > 0).sum();
        model.put("reservResumeAmountItem", Utils.formatDecimal((new BigDecimal(resumeAmount).divide(new BigDecimal(100)).setScale(2))));

        int resumeAfterTax = telesale.getOptions().get(0).getItems().stream().mapToInt(ItemReservationDTO::getAmountAfterTax).filter(n -> n > 0).sum();
        model.put("reservResumeValTax", Utils.formatDecimal((new BigDecimal(resumeAfterTax).subtract(
                new BigDecimal(resumeAmount))).divide(
                new BigDecimal(100)).setScale(2)));

        int resumeAmountTotal = telesale.getOptions().get(0).getItems().stream().mapToInt(ItemReservationDTO::getAmountAfterTax).filter(n -> n > 0).sum();
        String vlFormated = Utils.formatDecimal((new BigDecimal(resumeAmountTotal).divide(new BigDecimal(100)).setScale(2)));
        model.put("reservResumeAmountTotal", vlFormated);
        model.put("vlPaymant", vlFormated);
        model.put("total", vlFormated);
        if(Objects.equals(telesale.getHotel().getCode(), HotelType.ARAXA.getCode()))
            model.put("minhaurl", URL_SITE_ARAXA_RESERVES + URL_TELESALE_TAUA + telesale.getToken());
        else
            model.put("minhaurl", URL_SITE_TAUA_RESERVES + URL_TELESALE_TAUA + telesale.getToken());

        model.put("document", telesale.getCustomer().getDocument());
        model.put("hotel_code", telesale.getHotel().getCode());

        model.put("token", telesale.getToken());

        email.setModel(model);

        return email;
    }

    private void makeTariffDescription(TelesaleDTO telesale, Map<String, Object> model) {
        List<Map<String, Object>> listOfPolicies = new ArrayList<>();

        telesale.getOptions().forEach(option -> {


            List<PricingTariffRuleDTO> tariffs = option.getItems().stream()
                    .filter(itemOrder -> itemOrder.getTariffId() != null)
                    .map(ItemReservationDTO::getTariff).toList();

            tariffs.forEach(tariff->{
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
        });

        model.put("listPolicies", listOfPolicies);
    }


    private List<Map<String, Object>> listOptionsTeleSale(TelesaleDTO telesale) {
        List<Map<String, Object>> listOfLists = new ArrayList<>();

        telesale.getOptions().stream().forEach(option -> {
            Map<String, Object> model = new HashMap<>();
            int qtdAdults = option.getItems().stream().mapToInt(ItemReservationDTO::getAdults).filter(n -> n > 0).sum();
            model.put("adults", qtdAdults);
            int qtdchildrens = option.getItems().stream().mapToInt(ItemReservationDTO::getChildrens).filter(n -> n > 0).sum();
            model.put("childrens", qtdchildrens);

            model.put("listItens", listIensTeleSale(option.getItems()));

            int resumeAmount = option.getItems().stream().mapToInt(ItemReservationDTO::getAmountBeforeTax).filter(n -> n > 0).sum();
            model.put("reservResumeAmountItem", Utils.formatDecimal((new BigDecimal(resumeAmount).divide(new BigDecimal(100)).setScale(2))));

            int resumeAfterTax = option.getItems().stream().mapToInt(ItemReservationDTO::getAmountAfterTax).filter(n -> n > 0).sum();
            model.put("reservResumeValTax", Utils.formatDecimal((new BigDecimal(resumeAfterTax).subtract(
                    new BigDecimal(resumeAmount))).divide(new BigDecimal(100)).setScale(2)));

            int resumeAmountTotal = option.getItems().stream().mapToInt(ItemReservationDTO::getAmountAfterTax).filter(n -> n > 0).sum();
            String vlFormated = Utils.formatDecimal((new BigDecimal(resumeAmountTotal).divide(new BigDecimal(100)).setScale(2)));
            model.put("reservResumeAmountTotal", vlFormated);
            model.put("vlPaymant", vlFormated);
            model.put("total", vlFormated);
            model.put("minhaurl", makeLinkBudget(telesale, option, qtdAdults, qtdchildrens));

            listOfLists.add(model);
        });
        return listOfLists;
    }

    private String makeLinkBudget(TelesaleDTO telesale, OptionDTO option, int qtdAdults, int qtdChildren) {
        List<String> parameters = new ArrayList<>();
        parameters.add("hotel=".concat(telesale.getHotel().getCode().toString()));
        parameters.add("checkin=".concat(telesale.getHotel().getStayDateStart()));
        parameters.add("checkout=".concat(telesale.getHotel().getStayDateEnd()));
        parameters.add("adults=".concat(""+qtdAdults));
        parameters.add("children=".concat(""+qtdChildren));
        if (qtdChildren > 0) {
            option.getItems().forEach(item -> {
                item.getGuests().forEach(guest -> {
                    if (guest.getType().equals(AgeGroup.CHILDREN))
                        parameters.add("idades=".concat(guest.getAge()));
                });
            });
        }
        if (Objects.equals(telesale.getHotel().getCode(), HotelType.ARAXA.getCode()))
            return String.format("%s%s%s?", URL_SITE_ARAXA_RESERVES, URL_SEARCH_ARAXA_RESERVES, telesale.getHotel().getCode())
                    .concat(String.join("&", parameters));
        return String.format("%s%s%s?", URL_SITE_TAUA_RESERVES, URL_SEARCH_TAUA_RESERVES, telesale.getHotel().getCode())
                .concat(String.join("&", parameters));
    }

    private List<Map<String, Object>> listIensTeleSale(List<ItemReservationDTO> itemsTeleSale) {
        List<Map<String, Object>> listOfLists = new ArrayList<>();
        for (ItemReservationDTO item : itemsTeleSale) {
            Map<String, Object> list_array = new HashMap<>();
            list_array.put("description", item.getDescription());
            list_array.put("quantity", item.getQuantity());
            list_array.put("adults", item.getAdults());
            list_array.put("childrens", item.getChildrens());
            list_array.put("amount", Utils.formatDecimal((new BigDecimal(item.getAmount()).divide(new BigDecimal(100)).setScale(2))));

            if (item.getTariff() != null) {
                list_array.put("tariff_name", item.getTariff().getDisplayPrice().getLabelPublic());
            } else {
                list_array.put("tariff_name", "");
            }

            listOfLists.add(list_array);
        }
        return listOfLists;
    }
}

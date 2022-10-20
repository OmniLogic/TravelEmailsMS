package ai.omnilogic.travel.emails.services.reservation;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class SendEmailReservationServiceImpl implements SendEmailReservation {

    @Override
    public void sendRequesteReservation(OrderReserve orderReserve) {
        Mail email = createDataToSendByReservation(orderReserve, "Pedido Recebido!", false);

        if (Objects.equals(orderReserve.getHotelCode(), HotelType.ARAXA.getCode()))
            sendMail(email, "emailOrderReserveAraxa.ftl");
        else
            sendMail(email, "emailOrderReserve.ftl");
    }

    @Override
    public void reSendConfirmReserveMail(OrderReserve orderReserve, String login) {

    }

    private Mail createDataToSendByReservation(OrderReserve orderReserve, String title, boolean paid) throws IntegrationVoluxException {
        Mail email = new Mail();
        defineAraxaOrNo(email, orderReserve.getHotelCode());
        email.setHotelCode(orderReserve.getHotel().getCode());
        email.setReserveId(orderReserve.getReserveId());
        email.setTo(orderReserve.getCustomer().getEmail());
        email.setName(orderReserve.getCustomer().getName());
        email.setSubject(title);
        String number = "(31) 3236-1900";

        if(Optional.ofNullable(orderReserve.getTeleSaleId()).isPresent() && !orderReserve.getTeleSaleId().trim().isEmpty()){
            Telesale telesale = telesaleRepository.findBySaleId(orderReserve.getTeleSaleId())
                    .stream()
                    .findAny()
                    .orElseThrow(() -> new IntegrationVoluxException(String.format("Telesale Reservation Id %s not found!", orderReserve.getTeleSaleId())));;
            if(Optional.ofNullable(telesale.getIdSeller()).isPresent()
                    && !telesale.getIdSeller().trim().isEmpty()){
                Optional<Seller> seller = sellerRepository.findById(telesale.getIdSeller());
                seller.ifPresent(value -> email.setCc(value.getEmail()));
                if(paid)
                    number = "0800 333 1900";
            }
        }
        if(!Optional.ofNullable(email.getCc()).isPresent() || email.getCc().trim().isEmpty()){
            email.setBcc(TAUA_EMAIL_CONFIRM_RESERVATION);
        }

        Map<String, Object> model = new HashMap<>();
        List<Map<String, Object>> listOfPolicies = new ArrayList<>();

        if (orderReserve.getItems() != null && orderReserve.getItems().size() > 0
                && orderReserve.getItems().get(0).getTariffId() != null) {
            Set<String> collect = orderReserve.getItems().stream().map(itemOrder -> (itemOrder.getTariffId())).collect(Collectors.toSet());

            List<PricingTariffRule> tariffs = new ArrayList<>();
            collect.stream().forEach(item->{
                Optional<PricingTariffRule> tariff = tariffPriceRuleRepository.findById(item);
                if (tariff.isPresent())
                    tariffs.add(tariff.get());
            });

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
        model.put("name", orderReserve.getCustomer().getFullName());
        model.put("reserve_id", orderReserve.getReserveId());
        //TODO domingo tem horario de checkin as 17:00
        model.put("checkinDate", Utils.formatDateToBr(orderReserve.getHotel().getStayDateStart()));
        model.put("checkinHour", "Check-in segunda a sábado: 15:00h </br> e aos domingos: 17:00h");
        model.put("number", number);
        Optional<Hotel> hotel = hotelRepository.findByCode(orderReserve.getHotel().getCode()).stream().findFirst();
        String childText;
        if (hotel.isPresent())
            childText = hotel.get().getConfig().getChildren().getChildrenInformation();
        else
            childText = "02 Crianças de 0 a 12 anos se hospedam gratuitamente quando ocupam o quarto dos pais ou guardiões, usando as camas existentes. Os pais devem apresentar a certidão de nascimento e documentação da criança. Animais de estimação não são aceitos.";

        model.put("childText", childText);


        //TODO domingo tem horario de checkin as 17:00
        model.put("checkoutDate", Utils.formatDateToBr(orderReserve.getHotel().getStayDateEnd()));
        model.put("checkoutHour", "12:00");
        model.put("codReserve", orderReserve.getReserveId());
        model.put("hotelName", HotelType.getNameByCode(orderReserve.getHotel().getCode()).getName());
        model.put("hotelImage", Utils.imgHotel(orderReserve.getHotel().getType()));
        List<Map<String, Object>> listOfLists = new ArrayList<Map<String, Object>>();

        for (ItemOrder itemReserv : orderReserve.getItems()) {
            Map<String, Object> list_array = new HashMap<String, Object>();
            //Guest guest = itemOrder.getGuests().stream().filter( g -> g.getType().equals( "10" ) ).findFirst().orElse( new Guest() );
            Optional<PricingTariffRule> pricingRule = tariffPriceRuleRepository.
                    findById(itemReserv.getTariffId());

            if (pricingRule.isPresent()) {
                list_array.put("tariff_name", pricingRule.get().getDisplayPrice().getLabelPublic());
            } else {
                list_array.put("tariff_name", "");
            }

            list_array.put("reserv", itemReserv.getDescription());
            list_array.put("reservResumeDescriptionItem", itemReserv.getDescription());
            list_array.put("reservResumeAmountItem", Utils.formatDecimal((new BigDecimal(itemReserv.getAmountBeforeTax()).divide(new BigDecimal(100)).setScale(2))));
            list_array.put("reservResumeAmount", Utils.formatDecimal((new BigDecimal(itemReserv.getAmount()).divide(new BigDecimal(100)).setScale(2))));

            listOfLists.add(list_array);
        }

        if (orderReserve.getItemAditionalServices() != null &&
                orderReserve.getItemAditionalServices().size() > 0) {
            for (ItemAditionalService itemReserv : orderReserve.getItemAditionalServices()) {
                Map<String, Object> list_array = new HashMap<String, Object>();
                list_array.put("reserv", itemReserv.getDescription());
                list_array.put("reservResumeDescriptionItem", itemReserv.getDescription());
                list_array.put("reservResumeAmountItem", Utils.formatDecimal((new BigDecimal(itemReserv.getAmountAfterTax()).divide(new BigDecimal(100)).setScale(2))));
                list_array.put("reservResumeAmount", Utils.formatDecimal((new BigDecimal(itemReserv.getAmountAfterTax()).divide(new BigDecimal(100)).setScale(2))));
                listOfLists.add(list_array);
            }
        }
        model.put("listItens", listOfLists);

        int resumeAmountTotal = orderReserve.getTotals().getIss();
        model.put("reservResumeValTax", Utils.formatDecimal((new BigDecimal(resumeAmountTotal).divide(new BigDecimal(100)).setScale(2))));

        int resumeAmount = orderReserve.getTotals().getAmount();
        model.put("reservResumeAmount", Utils.formatDecimal((new BigDecimal(resumeAmount).divide(new BigDecimal(100)).setScale(2))));

        int roon = orderReserve.getItems().stream().mapToInt(ItemOrder::getQuantity).filter(n -> n > 0).sum();
        model.put("roon", roon);
        int adult = orderReserve.getItems().stream().mapToInt(ItemOrder::getAdults).filter(n -> n > 0).sum();
        model.put("adult", adult);
        int childreans = orderReserve.getItems().stream().mapToInt(ItemOrder::getChildrens).filter(n -> n > 0).sum();
        model.put("childreans", childreans);

        int amount = orderReserve.getTotals().getAmount();
        model.put("vlPaymant", Utils.formatDecimal((new BigDecimal(amount).divide(new BigDecimal(100)).setScale(2))));
        model.put("total", Utils.formatDecimal((new BigDecimal(amount).divide(new BigDecimal(100)).setScale(2))));

        email.setModel(model);

        return email;
    }
}

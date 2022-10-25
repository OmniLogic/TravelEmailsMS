package ai.omnilogic.travel.emails.dto.reservation;

import ai.omnilogic.travel.emails.dto.additional_service.AdditionalServiceDTO;
import ai.omnilogic.travel.emails.models.customer.CustomerDTO;
import ai.omnilogic.travel.emails.models.Totals;
import ai.omnilogic.travel.emails.models.hotel.Hotel;
import ai.omnilogic.travel.emails.models.payment.Payment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ReservationDTO {

    @JsonProperty("reserve_id")
    private String reserveId;

    @JsonProperty("hotel")
    private Hotel hotel;

    @JsonProperty("cancellation_request_date")
    private String cancellationRequestDate;

    @JsonProperty("hour_checkin_hotel")
    private String hourCheckInHotel;

    @JsonProperty("date")
    private String date;

    @JsonProperty("items")
    private List<ItemReservationDTO> items;

    @JsonProperty("customer")
    private CustomerDTO customer;

    @JsonProperty("sale_id")
    private String teleSaleId;

    @JsonProperty("is_telesale")
    private Boolean isTelesale;

    @JsonProperty("email_seller")
    private String emailCc;

    @JsonProperty("children_information")
    private String childrenInformation;

    @JsonProperty("totals")
    private Totals totals;

    @JsonProperty("item_aditional_service")
    private List<AdditionalServiceDTO> itemAditionalServices;

    @JsonProperty("payments")
    private List<Payment> payments;

    public String getCancellationRequestDate() {
        return this.cancellationRequestDate == null? LocalDateTime.now().toString() : this.cancellationRequestDate;
    }

    public Integer getHotelCode() {
        return this.getHotel().getCode();
    }

}

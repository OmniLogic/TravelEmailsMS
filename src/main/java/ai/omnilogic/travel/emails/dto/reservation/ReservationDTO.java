package ai.omnilogic.travel.emails.dto.reservation;

import ai.omnilogic.travel.emails.dto.additional_service.AdditionalServiceDTO;
import ai.omnilogic.travel.emails.models.Customer.CustomerDTO;
import ai.omnilogic.travel.emails.models.Totals;
import ai.omnilogic.travel.emails.models.hotel.Hotel;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ReservationDTO {

    @JsonProperty("reserve_id")
    private String reserveId;

    @JsonProperty("hotel")
    private Hotel hotel;

    @JsonProperty("items")
    private List<ItemReservationDTO> items;

    @JsonProperty("customer")
    private CustomerDTO customer;

    @JsonProperty("sale_id")
    private String teleSaleId;

    @JsonProperty("is_telesale")
    private Boolean isTelesale;

    @JsonProperty("email_seller")
    private String emailSeller;

    @JsonProperty("children_information")
    private String childrenInformation;

    @JsonProperty("totals")
    private Totals totals;

    @JsonProperty("item_aditional_service")
    private List<AdditionalServiceDTO> itemAditionalServices;

    public Integer getHotelCode() {
        return this.getHotel().getCode();
    }

}

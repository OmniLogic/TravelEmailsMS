package ai.omnilogic.travel.emails.dto.telesale;

import ai.omnilogic.travel.emails.models.Customer.CustomerDTO;
import ai.omnilogic.travel.emails.models.hotel.Hotel;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class TelesaleDTO {


    @JsonProperty("hotel")
    private Hotel hotel;

    @JsonProperty("sale_id")
    private String saleId;

    @JsonProperty("customer")
    private CustomerDTO customer;

    @JsonProperty("options")
    private List<OptionDTO> options;

    @JsonProperty("children_information")
    private String childrenInformation;

    @JsonProperty("city_hotel")
    private String cityHotel;

    @JsonProperty("hotel_name")
    private String hotelName;

    @JsonProperty("token")
    private String token;

    String emailSeller;

    @JsonProperty("date")
    private String date;

    @JsonProperty("validity_in_hours")
    private Integer validityInHours;

    public Integer getHotelCode() {
        return this.getHotel().getCode();
    }
}

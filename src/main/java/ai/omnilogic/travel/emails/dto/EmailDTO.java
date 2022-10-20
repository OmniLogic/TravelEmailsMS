package ai.omnilogic.travel.emails.dto;

import ai.omnilogic.travel.emails.models.hotel.HotelEmail;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class EmailDTO {

    private HotelEmail email;

    @JsonProperty("terms_policies_html")
    private String termPoliciesHtml;
}

package ai.omnilogic.travel.emails.dto.additional_service;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AdditionalServiceDTO {

    private String description;

    @JsonProperty("amount_after_tax")
    private int amountAfterTax;
}

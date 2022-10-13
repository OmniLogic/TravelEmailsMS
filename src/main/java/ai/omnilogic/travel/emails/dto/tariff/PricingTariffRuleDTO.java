package ai.omnilogic.travel.emails.dto.tariff;

import ai.omnilogic.travel.emails.models.tariff.DisplayPrice;
import ai.omnilogic.travel.emails.models.tariff.Refundable;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PricingTariffRuleDTO {

    private String id;

    @JsonProperty("display")
    private DisplayPrice displayPrice;

    @JsonProperty("refundable")
    private Refundable refundable;

}

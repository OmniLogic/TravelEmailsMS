package ai.omnilogic.travel.emails.dto.tariff;

import ai.omnilogic.travel.emails.models.tariff.DisplayPrice;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PricingTariffRuleDTO {
    @JsonProperty("display")
    private DisplayPrice displayPrice;

}

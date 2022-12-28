package ai.omnilogic.travel.emails.dto.reservation;

import ai.omnilogic.travel.emails.dto.tariff.PricingTariffRuleDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ItemReservationDTO {
    @JsonProperty("amount_before_tax")
    private int amountBeforeTax;

    @JsonProperty("amount_after_tax")
    private int amountAfterTax;

    @JsonProperty("adults")
    private int adults;

    @JsonProperty("childrens")
    private int childrens;

    @JsonProperty("quantity")
    private int quantity;

    @JsonProperty("description")
    private String description;

    @JsonProperty("code")
    private String code;

    @JsonProperty("tariff_id")
    private String tariffId;

    @JsonProperty("tariff")
    private PricingTariffRuleDTO tariff;

    @JsonProperty("discount_applied")
    private int discountApplied;

    @JsonProperty("guests")
    private List<Guest> guests;

    @JsonProperty("amount")
    public int getAmount() {
        return this.amountAfterTax;
    }
}

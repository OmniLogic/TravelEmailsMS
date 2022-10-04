package ai.omnilogic.travel.emails.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Totals {
    @JsonProperty("itens_amount_before_tax")
    private int itenAmountBeforeTax;

    @JsonProperty("itens_amount_after_tax")
    private int itensAmountAfterTax;

    @JsonProperty("discounts")
    private int discounts;

    @JsonProperty("iss_amount")
    private int iss;

    @JsonProperty("additional_services_amount")
    private int additionalServicesAmount;

    @JsonProperty("amount")
    private int amount;

    @JsonProperty("currency_code")
    private String currencyCode;
}

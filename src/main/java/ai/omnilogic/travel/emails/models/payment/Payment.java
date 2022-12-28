package ai.omnilogic.travel.emails.models.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @JsonProperty("payment_method")
    private PaymentMethod paymentMethod;

    @JsonProperty("external_id")
    private String externalId;

    @JsonProperty("amount")
    private Integer amount;

}

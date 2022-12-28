package ai.omnilogic.travel.emails.models.tariff;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Refundable {


    @JsonProperty("refundable")
    private Boolean refundable;

    @JsonProperty("refund_limit_hours")
    private Integer refundLimitHours;

    public Integer getRefundLimitHours() {
        return this.refundLimitHours == null? 72: this.refundLimitHours;
    }

    @Override
    public String toString() {
        return "PaymentPrice{" +
                ", refundable=" + refundable +
                ", refundLimitHours=" + refundLimitHours +
                '}';
    }
}

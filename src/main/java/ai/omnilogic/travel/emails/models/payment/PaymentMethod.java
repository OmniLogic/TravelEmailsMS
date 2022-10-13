package ai.omnilogic.travel.emails.models.payment;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.security.InvalidParameterException;
import java.util.Arrays;

public enum PaymentMethod {
    @JsonProperty("credit_card")
    CREDIT_CARD("credit_card"),

    @JsonProperty("pix")
    PIX("pix");

    private String name;

    PaymentMethod(String name) {
        this.name = name;
    }

    public static PaymentMethod getByName(String name){
        return Arrays.stream(PaymentMethod.values())
                .filter(pay -> pay.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> (new InvalidParameterException("")));
    }

    public String getName() {
        return name;
    }
}

package ai.omnilogic.travel.emails.enums;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum AgeGroup {
    @JsonProperty("10")
    @JsonAlias("adult")
    ADULT(10, "adult"),

    @JsonProperty("8")
    @JsonAlias("children")
    CHILDREN(8, "children"),

    @JsonProperty("14")
    @JsonAlias("children_not_pay")
    CHILDREN_N_PAY(14, "children");

    private final int code;
    private final String name;

    AgeGroup(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

}

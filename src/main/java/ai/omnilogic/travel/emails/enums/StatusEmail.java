package ai.omnilogic.travel.emails.enums;

import java.util.Arrays;

public enum StatusEmail {
    SENT("sent"),
    DELAYED("delayed"),
    DELIVERED("delivered"),
    REJECTED("rejected"),
    SCHEDULED("scheduled"),
    QUEUED("queued"),
    FAILED("failed"),
    OTHERS("others");

    private String name;

    StatusEmail(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static StatusEmail getByName(String name) {
        return Arrays.stream(StatusEmail.values())
                .filter(item -> item.getName().equals(name))
                .findFirst()
                .orElse(StatusEmail.OTHERS);
    }
}

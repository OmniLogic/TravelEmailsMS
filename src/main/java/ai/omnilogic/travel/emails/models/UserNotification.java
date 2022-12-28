package ai.omnilogic.travel.emails.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class UserNotification {

    private String id;
    private String name;
    private String email;
    private Boolean active;
    @JsonProperty("hotel_code")
    private List<Integer> hotelCode;

}

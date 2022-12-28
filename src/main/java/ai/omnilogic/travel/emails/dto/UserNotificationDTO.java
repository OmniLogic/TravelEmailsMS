package ai.omnilogic.travel.emails.dto;

import ai.omnilogic.travel.emails.models.UserNotification;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class UserNotificationDTO {
    @JsonProperty("reserve_id")
    private  String reserveId;

    @JsonProperty("user_notification")
    private  List<UserNotification> userNotifications;
}

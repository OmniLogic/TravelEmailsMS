package ai.omnilogic.travel.emails.models;

import ai.omnilogic.travel.emails.enums.StatusEmail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageStatus {
    private String email;
    private StatusEmail status;
    private String rejectReason;
    private String id;
}

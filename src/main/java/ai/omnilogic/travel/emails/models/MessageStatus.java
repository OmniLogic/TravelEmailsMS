package ai.omnilogic.travel.emails.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageStatus {
    private String email;
    private String status;
    private String rejectReason;
    private String id;
}

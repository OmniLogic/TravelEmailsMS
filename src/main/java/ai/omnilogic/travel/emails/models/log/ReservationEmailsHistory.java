package ai.omnilogic.travel.emails.models.log;

import lombok.Data;

@Data
public class ReservationEmailsHistory {
    private String date;
    private String to;
    private String subject;
    private Boolean sent;
}

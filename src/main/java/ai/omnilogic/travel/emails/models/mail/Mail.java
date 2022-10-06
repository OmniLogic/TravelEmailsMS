package ai.omnilogic.travel.emails.models.mail;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Map;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Mail {
    @JsonProperty("hotel_code")
    private Integer hotelCode;

    @JsonProperty("reserve_id")
    private String reserveId;

    private String from;
    private String to;
    private String cc;
    private String bcc;
    private String name;
    private String subject;
    private String content;
    private String replayTo;
    private Map<String, Object> model;
    private String template;
}

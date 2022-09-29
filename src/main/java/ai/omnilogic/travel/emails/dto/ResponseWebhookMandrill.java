package ai.omnilogic.travel.emails.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ResponseWebhookMandrill {
    private Long id;
    private String url;
    private String description;

    @JsonProperty("auth_key")
    private String authKey;

    private List<String> events;

    @JsonProperty("created_at")
    private String createAt;


    @JsonProperty("last_sent_at")
    private String lastSentAt;

    @JsonProperty("batches_sent")
    private Integer batchesSent;

    @JsonProperty("events_sent")
    private Integer eventsSent;

    @JsonProperty("last_error")
    private String lastError;
}

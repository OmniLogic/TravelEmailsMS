package ai.omnilogic.travel.emails.models.log;

import ai.omnilogic.travel.emails.models.mail.Mail;
import ai.omnilogic.travel.emails.utils.Utils;
import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.GeneratedValue;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.List;

@Data
@Container(containerName = "log_email")
public class LogEmail implements Serializable {
    @Id
    @GeneratedValue
    private String id;

    @PartitionKey("hotel_code")
    @JsonProperty("hotel_code")
    private Integer hotelCode;

    @JsonProperty("reserve_id")
    private String reserveId;

    @JsonProperty("status_external")
    private String statusExternal;

    @JsonProperty("mandrill_id")
    private List<String> mandrillId;

    private String template;
    private Mail email;
    private Boolean sent;

    @CreatedDate
    private String date;

    @CreatedBy
    @JsonProperty("created_by")
    private String createdBy;

    public LogEmail() {
        this.date = Utils.dateNow();
    }
}

package ai.omnilogic.travel.emails.models.hotel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelEmail {
    private String from;
    private String subject;
    private List<String> cc;
    private List<String> bcc;

    @JsonProperty("contact_phone")
    private String contactPhone;

    @JsonProperty("contact_email")
    private String contatEmail;
}

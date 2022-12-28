package ai.omnilogic.travel.emails.models.tariff;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DisplayPrice {

    @JsonProperty("label_public")
    private String labelPublic;

    @JsonProperty("priority")
    private Boolean priority;

    @JsonProperty("displayed")
    private Boolean displayed;

    @JsonProperty("policies_restrictions")
    private String policiesRestrictions;

    @JsonProperty("description")
    private String description;

    public Boolean getPriority() {
        return priority == null ? false : priority;
    }

    @Override
    public String toString() {
        return "DisplayPrice{" +
                "labelPublic='" + labelPublic + '\'' +
                ", priority=" + priority +
                ", displayed=" + displayed +
                ", policiesRestrictions='" + policiesRestrictions + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

package ai.omnilogic.travel.emails.dto.telesale;

import ai.omnilogic.travel.emails.dto.additional_service.AdditionalServiceDTO;
import ai.omnilogic.travel.emails.dto.reservation.ItemReservationDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class OptionDTO {

    @JsonProperty("items")
    private List<ItemReservationDTO> items;

    @JsonProperty("item_aditional_service")
    private List<AdditionalServiceDTO> AditionalServices;
}

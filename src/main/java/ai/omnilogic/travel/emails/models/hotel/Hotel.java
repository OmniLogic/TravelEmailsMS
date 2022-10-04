package ai.omnilogic.travel.emails.models.hotel;

import ai.omnilogic.travel.emails.utils.Utils;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {
    @JsonProperty("hotel_code")
    private Integer code;

    @JsonProperty("stay_date_start")
    private String stayDateStart;

    @JsonProperty("stay_date_end")
    private String stayDateEnd;

    private String name;

    public String getName(){
        if (this.name == null && this.code != null)
            return HotelType.getNameByCode(this.code).getName();
        return this.name;
    }

    public int getNightsStays() {
        try {
            return Utils.nightsStays(this.stayDateStart, this.stayDateEnd);
        } catch (Exception ex){
            ex.printStackTrace();
            return 0;
        }
    }

    public HotelType getType(){
        return HotelType.getNameByCode(this.code);
    }
}

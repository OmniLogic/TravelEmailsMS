package ai.omnilogic.travel.emails.models.hotel;

import java.security.InvalidParameterException;
import java.util.Arrays;

public enum HotelType {
    CAETE(1, "CAETE", "Tauá Resort Caeté"),
    ATIBAIA(2, "ATIBAIA", "Tauá Hotel e Convention Atibaia"),
    ARAXA(3, "ARAXA", "Tauá Grande Hotel de Araxá e Termas"),
    ALEXANIA(6, "ALEXANIA", "Tauá Hotel e Convention Alexânia"),
    ALEGRO(9, "ALEGRO", "Alegro Hotel By Tauá");

    private final Integer code;
    private final String name;
    private String description;

    HotelType(Integer code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
    }

    public static HotelType getNameByCode(Integer code){
        return Arrays.stream(HotelType.values())
                .filter(item -> item.code.equals(code))
                .findFirst()
                .orElseThrow(() -> new InvalidParameterException("HOTEL CODE NOT FOUND"));
    }

    public static HotelType getCodeByName(String name){
        return Arrays.stream(HotelType.values())
                .filter(item -> item.name.toLowerCase().equals(name.toLowerCase()))
                .findFirst()
                .orElseThrow(() -> new InvalidParameterException("HOTEL NAME NOT FOUND"));
    }

    public Integer getCode() {
        return code;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
}

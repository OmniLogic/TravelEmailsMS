package ai.omnilogic.travel.emails.models.customer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
public class CustomerDTO {

    private String name;

    @JsonProperty("email")
    private String email;

    @JsonProperty("document")
    private String document;

    private String firstName;

    private String lastName;

    public String getFullName() {
        return String.format("%s %s", firstName, lastName);
    }

    @JsonProperty("firstname")
    public String getFirstName(){
        if(Optional.ofNullable(this.name).isPresent())
            return this.name.split(" ")[0];
        else if(Optional.ofNullable(this.firstName).isPresent())
            return this.firstName;
        else
            return "";
    }

    @JsonProperty("lastname")
    public String getLastName(){
        if(Optional.ofNullable(this.name).isPresent()) {
            String[] array = this.name.split(" ");
            String[] listLastName = Arrays.copyOfRange(array, 1, array.length);

            return Arrays.stream(listLastName)
                    .collect(Collectors.joining(" "));
        }else if(Optional.ofNullable(this.lastName).isPresent())
            return this.lastName;
        else
            return "";
    }
}

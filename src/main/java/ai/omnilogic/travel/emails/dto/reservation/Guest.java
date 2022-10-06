package ai.omnilogic.travel.emails.dto.reservation;

import ai.omnilogic.travel.emails.enums.AgeGroup;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Guest {
    private AgeGroup type;
    private String name;
    private String email;
    private String age;

    @Override
    public String toString() {
        return "{" +
                "type=" + type +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}

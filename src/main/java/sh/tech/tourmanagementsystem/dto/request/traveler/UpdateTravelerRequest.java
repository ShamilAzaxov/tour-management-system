package sh.tech.tourmanagementsystem.dto.request.traveler;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateTravelerRequest {
    Long id;
    String firstName;
    String lastName;
    String email;
}

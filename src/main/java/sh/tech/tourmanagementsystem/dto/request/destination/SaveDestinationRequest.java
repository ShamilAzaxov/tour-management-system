package sh.tech.tourmanagementsystem.dto.request.destination;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SaveDestinationRequest{
    String location;
    String description; //a description of the place
    LocalDate visitDate; //the date the place is visited
    Long tourId;
}

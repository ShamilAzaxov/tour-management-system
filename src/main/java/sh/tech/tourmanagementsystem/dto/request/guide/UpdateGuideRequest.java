package sh.tech.tourmanagementsystem.dto.request.guide;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateGuideRequest {
    Long id;
    String name;
    String email;
    String phoneNumber;
}

package sh.tech.tourmanagementsystem.dto.request.guide;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import sh.tech.tourmanagementsystem.dto.request.passport.SavePassportRequest;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SaveGuideRequest {
    String name;
    String email;
    String phoneNumber;
    SavePassportRequest passport;
}

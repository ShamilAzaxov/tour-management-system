package sh.tech.tourmanagementsystem.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import sh.tech.tourmanagementsystem.dto.enums.GuideStatus;
import sh.tech.tourmanagementsystem.dto.response.passport.PassportResponseWithoutId;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GuideResponse {
    Long id;
    String name;
    String email;
    String phoneNumber;
    GuideStatus status;
    PassportResponseWithoutId passportResponse;
}

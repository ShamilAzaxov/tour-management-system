package sh.tech.tourmanagementsystem.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import sh.tech.tourmanagementsystem.dao.entity.Passport;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GuideResponse {
    String name;
    String email;
    String phoneNumber;
    Passport passport;
}

package sh.tech.tourmanagementsystem.dto.response.passport;

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
public class PassportResponse {
    Long id;
    String passportNumber;
    LocalDate issueDate;
    LocalDate expiryDate;
    String country;
}

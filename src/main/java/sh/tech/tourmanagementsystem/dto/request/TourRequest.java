package sh.tech.tourmanagementsystem.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TourRequest {
    Long id;
    String name;
    String description;
    BigDecimal price;
    LocalDate startDate;
    LocalDate endDate;
}
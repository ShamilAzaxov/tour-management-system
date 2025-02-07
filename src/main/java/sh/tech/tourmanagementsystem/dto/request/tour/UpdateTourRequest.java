package sh.tech.tourmanagementsystem.dto.request.tour;

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
public class UpdateTourRequest {
    Long id;
    String name;
    String description;
    BigDecimal price;
    LocalDate startDate;
    LocalDate endDate;
}

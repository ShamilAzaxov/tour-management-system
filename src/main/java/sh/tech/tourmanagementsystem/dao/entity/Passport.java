package sh.tech.tourmanagementsystem.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "passports")
public class Passport {
    @Id
    Long id;
    String passportNumber;
    LocalDate issueDate;
    LocalDate expiryDate;
    String country;
    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    @ToString.Exclude
    @JsonIgnore
    Guide guide;
}

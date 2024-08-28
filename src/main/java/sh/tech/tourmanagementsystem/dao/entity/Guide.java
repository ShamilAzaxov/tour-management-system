package sh.tech.tourmanagementsystem.dao.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import sh.tech.tourmanagementsystem.dto.enums.GuideStatus;

import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;

@Data
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "guides")
public class Guide {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String email;
    String phoneNumber;
    @Enumerated(STRING)
    GuideStatus status = GuideStatus.FREE;
    @OneToOne(mappedBy = "guide", cascade = ALL)
    @ToString.Exclude
    Passport passport;
    @ManyToMany(fetch = LAZY, mappedBy = "guides")
    List<Tour> tours;
}

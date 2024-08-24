package sh.tech.tourmanagementsystem.dao.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "tours")
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String description;
    BigDecimal price;
    LocalDate startDate;
    LocalDate endDate;
    @OneToMany(mappedBy = "tour", cascade = CascadeType.ALL)
    List<Destination> destination;
    @ManyToMany
    @JoinTable(name = "tour_guide",
            joinColumns = @JoinColumn(name = "tour_id"),
            inverseJoinColumns = @JoinColumn(name = "guide_id"))
    @ToString.Exclude
    Set<Guide> guides;
    @ManyToMany
    @JoinTable(name = "tour_traveler",
            joinColumns = @JoinColumn(name = "tour_id"),
            inverseJoinColumns = @JoinColumn(name = "traveler_id"))
    @ToString.Exclude
    List<Traveler> travelers;
}

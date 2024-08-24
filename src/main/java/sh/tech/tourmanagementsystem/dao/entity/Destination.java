package sh.tech.tourmanagementsystem.dao.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;


import java.time.LocalDate;

import static jakarta.persistence.FetchType.LAZY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "destinations")
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String location;
    String description; //a description of the place
    LocalDate visitDate; //the date the place is visited
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "tour_id", referencedColumnName = "id")
    Tour tour;
}

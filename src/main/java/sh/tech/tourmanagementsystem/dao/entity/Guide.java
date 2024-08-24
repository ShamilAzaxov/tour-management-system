package sh.tech.tourmanagementsystem.dao.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

import static jakarta.persistence.CascadeType.ALL;

@Data
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
    @OneToOne(mappedBy = "guide", cascade = ALL, orphanRemoval = true)
    @ToString.Exclude
    Passport passport;
    @ManyToMany(mappedBy = "guides")
    Set<Tour> tours;
}

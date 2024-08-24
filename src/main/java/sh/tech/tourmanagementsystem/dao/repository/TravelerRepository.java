package sh.tech.tourmanagementsystem.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sh.tech.tourmanagementsystem.dao.entity.Traveler;

public interface TravelerRepository extends JpaRepository<Traveler, Long> {
}

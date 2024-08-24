package sh.tech.tourmanagementsystem.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sh.tech.tourmanagementsystem.dao.entity.Tour;

public interface TourRepository extends JpaRepository<Tour, Long> {
}

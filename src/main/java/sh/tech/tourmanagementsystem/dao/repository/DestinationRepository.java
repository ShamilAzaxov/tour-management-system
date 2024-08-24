package sh.tech.tourmanagementsystem.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sh.tech.tourmanagementsystem.dao.entity.Destination;

public interface DestinationRepository extends JpaRepository<Destination, Long> {
}

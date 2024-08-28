package sh.tech.tourmanagementsystem.dao.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import sh.tech.tourmanagementsystem.dao.entity.Destination;

import java.util.List;

public interface DestinationRepository extends JpaRepository<Destination, Long> {
    @EntityGraph(attributePaths = {"tour"})
    List<Destination> findByTourId(Long tourId);
}

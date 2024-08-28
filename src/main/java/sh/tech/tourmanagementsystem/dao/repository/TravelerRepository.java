package sh.tech.tourmanagementsystem.dao.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sh.tech.tourmanagementsystem.dao.entity.Tour;
import sh.tech.tourmanagementsystem.dao.entity.Traveler;

import java.util.List;
import java.util.Optional;

public interface TravelerRepository extends JpaRepository<Traveler, Long> {
    @Query("select t.tours from Traveler t where t.id = :travelerId")
    List<Tour> findToursByTravelerId(Long travelerId);
    @Override
    @EntityGraph(attributePaths = {"tours"})
    Optional<Traveler> findById(Long id);
}

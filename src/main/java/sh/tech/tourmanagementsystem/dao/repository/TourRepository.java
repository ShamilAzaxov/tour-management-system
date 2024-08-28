package sh.tech.tourmanagementsystem.dao.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sh.tech.tourmanagementsystem.dao.entity.Guide;
import sh.tech.tourmanagementsystem.dao.entity.Tour;
import sh.tech.tourmanagementsystem.dao.entity.Traveler;

import java.util.List;
import java.util.Optional;

public interface TourRepository extends JpaRepository<Tour, Long> {
    @Override
    @EntityGraph(attributePaths = {"destinations", "travelers"})
    Optional<Tour> findById(Long id);

    @Query("select t.travelers from Tour t where t.id = :tourId")
    List<Traveler> findTravelersByTourId(Long tourId);
    @Query("select t.guides from Tour t where t.id = :tourId")
    List<Guide> findGuideByTourId(Long tourId);
}

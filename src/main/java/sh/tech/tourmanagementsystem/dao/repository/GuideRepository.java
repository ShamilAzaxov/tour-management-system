package sh.tech.tourmanagementsystem.dao.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import sh.tech.tourmanagementsystem.dao.entity.Guide;
import sh.tech.tourmanagementsystem.dto.enums.GuideStatus;

import java.util.List;
import java.util.Optional;

public interface GuideRepository extends JpaRepository<Guide, Long> {
    @Override
    @EntityGraph(attributePaths = {"passport"})
    Optional<Guide> findById(Long id);

    @Override
    @EntityGraph(attributePaths = {"passport"})
    List<Guide> findAll();

    @Query(value = "UPDATE guides g SET status = 'FREE' WHERE g.status = 'BUSY' AND NOT " +
            "EXISTS (SELECT 1 FROM tour_guide tg JOIN tours t ON tg.tour_id = t.id " +
            "WHERE tg.guide_id = g.id AND t.end_date >= CURRENT_DATE);", nativeQuery = true)
    @Modifying
    void updateGuidesStatus();

    @EntityGraph(attributePaths = {"passport"})
    List<Guide> findByStatus(GuideStatus status);
}

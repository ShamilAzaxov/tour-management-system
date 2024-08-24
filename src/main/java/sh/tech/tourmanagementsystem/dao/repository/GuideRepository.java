package sh.tech.tourmanagementsystem.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sh.tech.tourmanagementsystem.dao.entity.Guide;

public interface GuideRepository extends JpaRepository<Guide, Long> {
}

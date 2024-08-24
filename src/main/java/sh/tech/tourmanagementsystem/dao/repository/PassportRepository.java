package sh.tech.tourmanagementsystem.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sh.tech.tourmanagementsystem.dao.entity.Passport;

import java.util.Optional;

public interface PassportRepository extends JpaRepository<Passport, Long> {
    Optional<Passport> findPassportByPassportNumber(String passportNumber);
}

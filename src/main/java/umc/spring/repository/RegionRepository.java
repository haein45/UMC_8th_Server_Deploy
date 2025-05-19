package umc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.entity.Region;

public interface RegionRepository extends JpaRepository<Region, Long> {
}

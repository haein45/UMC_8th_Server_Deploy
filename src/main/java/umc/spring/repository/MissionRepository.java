package umc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.entity.Mission;
import umc.spring.repository.mission.MissionRepositoryCustom;

public interface MissionRepository extends JpaRepository<Mission, Long>, MissionRepositoryCustom {
}
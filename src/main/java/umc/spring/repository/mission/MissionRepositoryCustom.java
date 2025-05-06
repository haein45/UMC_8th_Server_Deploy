package umc.spring.repository.mission;

import umc.spring.domain.entity.Mission;

import java.util.List;

public interface MissionRepositoryCustom {
    List<Mission> findAvailableMissions(Long memberId, String regionName, Long cursor, int limit);
}
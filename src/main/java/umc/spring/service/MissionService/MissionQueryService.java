package umc.spring.service.MissionService;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.domain.entity.Mission;
import umc.spring.repository.MissionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryService {

    private final MissionRepository missionRepository;

    public List<Mission> getHomeMissions(Long memberId, String regionName, Long cursor, int limit) {
        return missionRepository.findAvailableMissions(memberId, regionName, cursor, limit);
    }
}
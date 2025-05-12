package umc.spring.service.MissionService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import umc.spring.domain.entity.Mission;
import umc.spring.repository.MissionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl implements MissionQueryService {

    private final MissionRepository missionRepository;

    @Override
    public List<Mission> getHomeMissions(Long memberId, String regionName, Long cursor, int limit) {
        return missionRepository.findAvailableMissions(memberId, regionName, cursor, limit);
    }
}

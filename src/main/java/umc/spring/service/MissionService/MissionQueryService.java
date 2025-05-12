package umc.spring.service.MissionService;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.domain.entity.Mission;
import umc.spring.repository.MissionRepository;

import java.util.List;

public interface MissionQueryService {
    List<Mission> getHomeMissions(Long memberId, String regionName, Long cursor, int limit);
}
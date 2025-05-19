package umc.spring.service.MissionService;

import umc.spring.web.dto.mission.MissionRequestDTO;
import umc.spring.web.dto.mission.MissionResponseDTO;

public interface MissionCommandService {
    MissionResponseDTO createMission(Long storeId, MissionRequestDTO request);
}
package umc.spring.converter;

import umc.spring.domain.entity.Mission;
import umc.spring.domain.entity.Store;
import umc.spring.web.dto.mission.MissionRequestDTO;

public class MissionConverter {

    public static Mission toMission(MissionRequestDTO dto, Store store) {
        return Mission.builder()
                .store(store)
                .region(store.getRegion())
                .reward(dto.getReward())
                .missionSpec(dto.getMissionSpec())
                .deadline(dto.getDeadline())
                .build();
    }
}
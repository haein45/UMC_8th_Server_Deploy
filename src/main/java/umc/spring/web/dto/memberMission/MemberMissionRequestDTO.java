package umc.spring.web.dto.memberMission;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.spring.validation.annotation.NotDuplicatedChallenge;

@Getter
public class MemberMissionRequestDTO {

    @NotNull(message = "mission_id는 필수입니다.")
    @NotDuplicatedChallenge
    private Long missionId;
}

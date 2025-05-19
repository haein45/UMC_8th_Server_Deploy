package umc.spring.web.dto.mission;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MissionResponseDTO {
    private Long missionId;
    private String message;
}
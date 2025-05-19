package umc.spring.web.dto.mission;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class MissionRequestDTO {

    @NotNull(message = "리워드는 필수입니다.")
    @Min(value = 1, message = "리워드는 1 이상이어야 합니다.")
    private Integer reward;

    @NotBlank(message = "미션 설명은 필수입니다.")
    private String missionSpec;

    @NotNull(message = "마감일은 필수입니다.")
    @Future(message = "마감일은 미래 날짜여야 합니다.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate deadline;
}
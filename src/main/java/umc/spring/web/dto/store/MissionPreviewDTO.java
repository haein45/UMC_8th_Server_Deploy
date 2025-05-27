package umc.spring.web.dto.store;
import lombok.*;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MissionPreviewDTO {
    private String content;
    private int point;
    private LocalDate deadline;
}
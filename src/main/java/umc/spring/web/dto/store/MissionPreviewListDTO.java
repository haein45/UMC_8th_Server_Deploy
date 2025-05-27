package umc.spring.web.dto.store;

import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MissionPreviewListDTO {
    private List<MissionPreviewDTO> missions;
    private int totalPage;
    private long totalElements;
    private boolean isFirst;
    private boolean isLast;
}

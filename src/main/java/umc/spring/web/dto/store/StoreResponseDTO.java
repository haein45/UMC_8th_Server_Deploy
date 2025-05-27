package umc.spring.web.dto.store;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StoreResponseDTO {
    private Long id;
    private String name;
    private String address;
}


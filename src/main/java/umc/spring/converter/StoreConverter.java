package umc.spring.converter;

import umc.spring.domain.entity.Region;
import umc.spring.domain.entity.Store;
import umc.spring.web.dto.store.StoreRequestDTO;

public class StoreConverter {
    public static Store toStore(StoreRequestDTO dto, Region region) {
        return Store.builder()
                .name(dto.getName())
                .address(dto.getAddress())
                .score(dto.getScore())
                .region(region)
                .build();
    }
}


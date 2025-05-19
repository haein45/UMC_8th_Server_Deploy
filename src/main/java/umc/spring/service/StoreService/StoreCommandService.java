package umc.spring.service.StoreService;

import umc.spring.domain.entity.Store;
import umc.spring.web.dto.store.StoreRequestDTO;
import umc.spring.web.dto.store.StoreResponseDTO;

public interface StoreCommandService {
    StoreResponseDTO addStore(StoreRequestDTO request);
}


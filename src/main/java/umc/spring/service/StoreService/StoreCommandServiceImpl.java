package umc.spring.service.StoreService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.domain.entity.Region;
import umc.spring.domain.entity.Store;
import umc.spring.repository.RegionRepository;
import umc.spring.repository.StoreRepository.StoreRepository;
import umc.spring.web.dto.store.StoreRequestDTO;
import umc.spring.web.dto.store.StoreResponseDTO;

@Service
@RequiredArgsConstructor
@Transactional
public class StoreCommandServiceImpl implements StoreCommandService {

    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;

    @Override
    public StoreResponseDTO addStore(StoreRequestDTO request) {

        Region region = regionRepository.findById(request.getRegionId())
                .orElseThrow(() -> new RuntimeException("지역을 찾을 수 없습니다."));

        Store store = Store.builder()
                .region(region)
                .name(request.getName())
                .address(request.getAddress())
                .score(request.getScore())
                .build();

        Store saved = storeRepository.save(store);

        return new StoreResponseDTO(saved.getId(), saved.getName(), saved.getAddress());
    }
}
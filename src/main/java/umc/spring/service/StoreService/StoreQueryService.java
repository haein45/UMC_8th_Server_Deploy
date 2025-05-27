package umc.spring.service.StoreService;

import org.springframework.data.domain.Page;
import umc.spring.domain.entity.Mission;
import umc.spring.domain.entity.Review;
import umc.spring.domain.entity.Store;

import java.util.List;
import java.util.Optional;

public interface StoreQueryService {

    Optional<Store> findStore(Long id);
    List<Store> findStoresByNameAndScore(String name, Float score);
    Page<Mission> getMissionListByStoreId(Long storeId, int page);
    Page<Review> getReviewList(Long storeId, Integer page);
}
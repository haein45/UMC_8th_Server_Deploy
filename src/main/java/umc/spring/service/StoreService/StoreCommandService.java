package umc.spring.service.StoreService;

import org.springframework.web.multipart.MultipartFile;
import umc.spring.domain.entity.Review;
import umc.spring.domain.entity.Store;
import umc.spring.web.dto.review.ReviewRequestDTO;
import umc.spring.web.dto.store.StoreRequestDTO;
import umc.spring.web.dto.store.StoreResponseDTO;

public interface StoreCommandService {
    StoreResponseDTO addStore(StoreRequestDTO request);
    Review createReview(Long memberId, Long storeId, ReviewRequestDTO request, MultipartFile reviewPicture);

}


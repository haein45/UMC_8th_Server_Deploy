package umc.spring.service.MemberService;

import org.springframework.data.domain.Page;
import umc.spring.domain.entity.Review;

public interface MemberQueryService {
    Page<Review> getMyReviews(Long memberId, int page);
}
package umc.spring.service.MemberService;

import org.springframework.data.domain.Page;
import umc.spring.domain.entity.Review;
import umc.spring.domain.mapping.MemberMission;

public interface MemberQueryService {
    Page<Review> getMyReviews(Long memberId, int page);
    Page<MemberMission> getInProgressMissions(Long memberId, int i);
}
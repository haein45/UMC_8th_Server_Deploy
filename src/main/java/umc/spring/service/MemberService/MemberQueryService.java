package umc.spring.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import umc.spring.domain.entity.Review;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.member.MemberResponseDTO;

public interface MemberQueryService {
    Page<Review> getMyReviews(Long memberId, int page);
    Page<MemberMission> getInProgressMissions(Long memberId, int i);
    MemberResponseDTO.MemberInfoDTO getMemberInfo(HttpServletRequest request);
}
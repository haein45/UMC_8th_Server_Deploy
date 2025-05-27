package umc.spring.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import umc.spring.apiPayload.exception.CustomException;
import umc.spring.domain.entity.Member;
import umc.spring.domain.entity.Review;
import umc.spring.repository.ReviewRepository;
import umc.spring.repository.member.MemberRepository;
import umc.spring.apiPayload.exception.CustomException;
import org.springframework.data.domain.Pageable;

@Service
@RequiredArgsConstructor
public class MemberQueryServiceImpl implements MemberQueryService {

    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public Page<Review> getMyReviews(Long memberId, int page) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException("해당 멤버가 존재하지 않습니다."));
        return reviewRepository.findAllByMember(member, PageRequest.of(page, 10));
    }
}

package umc.spring.service.ReviewService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.domain.entity.Member;
import umc.spring.domain.entity.Review;
import umc.spring.domain.entity.Store;
import umc.spring.repository.member.MemberRepository;
import umc.spring.repository.ReviewRepository;
import umc.spring.repository.StoreRepository.StoreRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewCommandService {

    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;

    public void saveReview(Long memberId, Long storeId, String body, Float score) {

        Member member = memberRepository.findByIdUsingQueryDSL(memberId);
        Store store = storeRepository.findByIdUsingQueryDSL(storeId);

        Review review = Review.builder()
                .member(member)
                .store(store)
                .score(score)
                .title(body)
                .build();

        reviewRepository.save(review);
    }
}
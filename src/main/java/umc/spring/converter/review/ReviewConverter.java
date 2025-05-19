package umc.spring.converter.review;


import umc.spring.domain.entity.Member;
import umc.spring.domain.entity.Review;
import umc.spring.domain.entity.Store;
import umc.spring.web.dto.review.ReviewRequestDTO;

public class ReviewConverter {
    public static Review toReviewEntity(ReviewRequestDTO dto, Member member, Store store) {
        return Review.builder()
                .store(store)
                .member(member)
                .score(dto.getScore())
                .body(dto.getBody())
                .build();
    }
}

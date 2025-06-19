package umc.spring.converter.review;

import umc.spring.domain.entity.Member;
import umc.spring.domain.entity.Review;
import umc.spring.domain.entity.Store;
import umc.spring.domain.entity.ReviewImage;  // 이 부분 import 추가해야 함
import umc.spring.web.dto.review.ReviewRequestDTO;

public class ReviewConverter {
    public static Review toReviewEntity(ReviewRequestDTO dto, Member member, Store store) {
        return Review.builder()
                .store(store)
                .member(member)
                .score(dto.getScore())
                .content(dto.getBody())
                .build();
    }

    public static ReviewImage toReviewImage(String imageUrl, Review review) {
        return ReviewImage.builder()
                .imageUrl(imageUrl)
                .review(review)
                .build();
    }
}

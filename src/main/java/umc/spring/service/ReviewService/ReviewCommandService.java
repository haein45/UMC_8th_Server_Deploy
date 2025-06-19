//package umc.spring.service.ReviewService;
//
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import umc.spring.converter.review.ReviewConverter;
//import umc.spring.domain.entity.Member;
//import umc.spring.domain.entity.Review;
//import umc.spring.domain.entity.Store;
//import umc.spring.repository.member.MemberRepository;
//import umc.spring.repository.ReviewRepository;
//import umc.spring.repository.StoreRepository.StoreRepository;
//import umc.spring.web.dto.review.ReviewRequestDTO;
//
//@Service
//@RequiredArgsConstructor
//@Transactional
//public class ReviewCommandService {
//
//    private final MemberRepository memberRepository;
//    private final StoreRepository storeRepository;
//    private final ReviewRepository reviewRepository;
//
//    public void writeReview(ReviewRequestDTO dto) {
//        Long memberId = 1L;
//
//        Member member = memberRepository.findById(memberId)
//                .orElseThrow(() -> new RuntimeException("하드코딩 멤버가 존재하지 않음"));
//        Store store = storeRepository.findById(dto.getStoreId())
//                .orElseThrow(() -> new RuntimeException("storeId로 가게를 찾을 수 없습니다."));
//
//        Review review = ReviewConverter.toReviewEntity(dto, member, store);
//        reviewRepository.save(review);
//    }
//}
package umc.spring.service.ReviewService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import umc.spring.aws.s3.AmazonS3Manager;
import umc.spring.aws.s3.Uuid;
import umc.spring.converter.review.ReviewConverter;
import umc.spring.domain.entity.Member;
import umc.spring.domain.entity.Review;
import umc.spring.domain.entity.Store;
import umc.spring.repository.member.MemberRepository;
import umc.spring.repository.ReviewRepository;
import umc.spring.repository.StoreRepository.StoreRepository;
import umc.spring.web.dto.review.ReviewRequestDTO;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewCommandService {

    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;
    private final AmazonS3Manager s3Manager;

    public void writeReview(ReviewRequestDTO dto) {
        Long memberId = 1L;

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("하드코딩 멤버가 존재하지 않음"));
        Store store = storeRepository.findById(dto.getStoreId())
                .orElseThrow(() -> new RuntimeException("storeId로 가게를 찾을 수 없습니다."));

        Review review = ReviewConverter.toReviewEntity(dto, member, store);
        reviewRepository.save(review);
    }

    // ✅ 이미지 포함 리뷰 작성
    public void writeReviewWithImage(ReviewRequestDTO dto, MultipartFile reviewPicture) {
        Long memberId = 1L;

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("하드코딩 멤버가 존재하지 않음"));
        Store store = storeRepository.findById(dto.getStoreId())
                .orElseThrow(() -> new RuntimeException("storeId로 가게를 찾을 수 없습니다."));

        Review review = ReviewConverter.toReviewEntity(dto, member, store);
        reviewRepository.save(review); // 먼저 리뷰 저장

//        // 🔐 UUID 생성 및 저장
//        String uuid = UUID.randomUUID().toString();
//        Uuid savedUuid = uuidRepository.save(Uuid.builder().uuid(uuid).build());
//
//        // 📦 S3에 파일 업로드
//        String keyName = s3Manager.generateReviewKeyName(savedUuid);
//        String imageUrl = s3Manager.uploadFile(keyName, reviewPicture);
//
//        // 🖼️ 이미지 엔티티 저장
//        ReviewImage reviewImage = ReviewImage.builder()
//                .imageUrl(imageUrl)
//                .review(review)
//                .build();
//        reviewImageRepository.save(reviewImage);
    }
}

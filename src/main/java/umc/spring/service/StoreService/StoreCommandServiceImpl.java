package umc.spring.service.StoreService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import umc.spring.aws.s3.AmazonS3Manager;
import umc.spring.aws.s3.Uuid;
import umc.spring.converter.StoreConverter;
import umc.spring.converter.review.ReviewConverter;
import umc.spring.domain.entity.*;
import umc.spring.repository.*;
import umc.spring.repository.StoreRepository.StoreRepository;
import umc.spring.repository.member.MemberRepository;
import umc.spring.web.dto.review.ReviewRequestDTO;
import umc.spring.web.dto.store.StoreRequestDTO;
import umc.spring.web.dto.store.StoreResponseDTO;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class StoreCommandServiceImpl implements StoreCommandService {

    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final AmazonS3Manager s3Manager;
    private final UuidRepository uuidRepository;
    private final ReviewImageRepository reviewImageRepository;

    @Override
    public StoreResponseDTO addStore(StoreRequestDTO request) {
        Region region = regionRepository.findById(request.getRegionId())
                .orElseThrow(() -> new RuntimeException("지역을 찾을 수 없습니다."));

        Store store = Store.builder()
                .region(region)
                .name(request.getName())
                .address(request.getAddress())
                .score(request.getScore())
                .build();

        Store saved = storeRepository.save(store);
        return new StoreResponseDTO(saved.getId(), saved.getName(), saved.getAddress());
    }

    @Override
    public Review createReview(Long memberId, Long storeId, ReviewRequestDTO request, MultipartFile reviewPicture) {

        // 1. Member & Store 조회
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("가게를 찾을 수 없습니다."));

        // 2. 리뷰 생성 (builder에 member, store까지 포함)
        Review review = Review.builder()
                .title(request.getTitle())
                .content(request.getBody())
                .score(request.getScore())
                .member(member)
                .store(store)
                .build();

        // 3. UUID 생성 및 S3 업로드
        String uuid = UUID.randomUUID().toString();
        Uuid savedUuid = uuidRepository.save(Uuid.builder().uuid(uuid).build());
        String pictureUrl = s3Manager.uploadFile(s3Manager.generateReviewKeyName(savedUuid), reviewPicture);

        // 4. 리뷰 이미지 저장
        reviewImageRepository.save(ReviewConverter.toReviewImage(pictureUrl, review));

        // 5. 리뷰 저장
        return reviewRepository.save(review);
    }

}

package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import umc.spring.service.ReviewService.ReviewCommandService;
import umc.spring.web.dto.review.ReviewRequestDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewRestController {

    private final ReviewCommandService reviewCommandService;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> createReview(
            @RequestPart("request") @Valid ReviewRequestDTO dto,
            @RequestPart("reviewPicture") MultipartFile reviewPicture
    ) {
        reviewCommandService.writeReviewWithImage(dto, reviewPicture);
        return ResponseEntity.ok("리뷰 작성 + 이미지 업로드 완료되었습니다.");
    }
//    @PostMapping
//    public ResponseEntity<String> createReview(@RequestBody @Valid ReviewRequestDTO dto) {
//        reviewCommandService.writeReview(dto);
//        return ResponseEntity.ok("리뷰 작성되었습니다.");
//    }

}
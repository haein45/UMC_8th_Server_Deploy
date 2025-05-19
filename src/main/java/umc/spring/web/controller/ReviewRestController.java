package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.service.ReviewService.ReviewCommandService;
import umc.spring.web.dto.review.ReviewRequestDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewRestController {

    private final ReviewCommandService reviewCommandService;

    @PostMapping
    public ResponseEntity<String> createReview(@RequestBody @Valid ReviewRequestDTO dto) {
        reviewCommandService.writeReview(dto);
        return ResponseEntity.ok("리뷰 작성되었습니다.");
    }
}
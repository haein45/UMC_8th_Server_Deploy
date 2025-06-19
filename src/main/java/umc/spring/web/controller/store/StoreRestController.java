package umc.spring.web.controller.store;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.entity.Mission;
import umc.spring.domain.entity.Review;
import umc.spring.service.MemberService.MemberCommandService;
import umc.spring.service.StoreService.StoreCommandService;
import umc.spring.service.StoreService.StoreQueryService;
import umc.spring.validation.ValidPage;
import umc.spring.validation.annotation.ExistMember;
import umc.spring.validation.annotation.ExistStore;
import umc.spring.web.dto.review.ReviewRequestDTO;
import umc.spring.web.dto.store.MissionPreviewListDTO;
import umc.spring.web.dto.store.StoreRequestDTO;
import umc.spring.web.dto.store.StoreResponseDTO;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreRestController {

    private final StoreCommandService storeCommandService;
    private final StoreQueryService storeQueryService;
    private final MemberCommandService memberCommandService;


    @PostMapping
    public StoreResponseDTO addStore(@RequestBody @Valid StoreRequestDTO request) {
        return storeCommandService.addStore(request);
    }

    @GetMapping("/{storeId}/missions")
    @Operation(summary = "가게 미션 목록 조회", description = "특정 가게의 미션 목록을 페이징으로 조회합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "VALID400", description = "page는 1 이상의 값이어야 함")
    })
    public ApiResponse<MissionPreviewListDTO> getStoreMissions(
            @PathVariable Long storeId,
            @ValidPage @RequestParam int page
    ) {
        Page<Mission> missionPage = storeQueryService.getMissionListByStoreId(storeId, page - 1);
        return ApiResponse.onSuccess(StoreConverter.toMissionPreviewListDTO(missionPage));
    }

    @GetMapping("/{storeId}/reviews")
    @Operation(summary = "특정 가게의 리뷰 목록 조회 API", description = "가게의 리뷰 목록을 페이징 처리하여 반환")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "STORE404", description = "존재하지 않는 가게")
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게 ID (path variable)"),
            @Parameter(name = "page", description = "조회할 페이지 번호 (query string)")
    })
    public ApiResponse<StoreResponseDTO.ReviewPreViewListDTO> getReviewList(
            @ExistStore @PathVariable(name = "storeId") Long storeId,
            @RequestParam(name = "page") Integer page) {

        Page<Review> reviewPage = storeQueryService.getReviewList(storeId, page);
        StoreResponseDTO.ReviewPreViewListDTO responseDTO = StoreConverter.reviewPreViewListDTO(reviewPage);
        return ApiResponse.onSuccess(responseDTO);
    }

    @PostMapping(value = "/{storeId}/reviews", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @Operation(summary = "리뷰 등록 (사진 포함)", description = "리뷰 내용과 사진을 함께 업로드합니다.")
    public ApiResponse<StoreResponseDTO.CreateReviewResultDTO> createReview(
            @Parameter(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
            @RequestPart("request") @Valid ReviewRequestDTO request,
            @ExistStore @PathVariable(name = "storeId") Long storeId,
            @ExistMember @RequestParam(name = "memberId") Long memberId,
            @RequestPart("reviewPicture") MultipartFile reviewPicture) {

        Review review = storeCommandService.createReview(memberId, storeId, request, reviewPicture);
        return ApiResponse.onSuccess(StoreConverter.toCreateReviewResultDTO(review));
    }

    @DeleteMapping("/{memberId}/profile-image")
    @Operation(summary = "프로필 이미지 삭제", description = "회원의 프로필 이미지를 삭제합니다.")
    public ApiResponse<String> deleteProfileImage(@PathVariable Long memberId) {
        memberCommandService.deleteProfileImage(memberId);
        return ApiResponse.onSuccess("프로필 이미지가 삭제되었습니다.");
    }



}

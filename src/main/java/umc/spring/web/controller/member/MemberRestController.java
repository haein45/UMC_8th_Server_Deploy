package umc.spring.web.controller.member;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.member.MemberConverter;
import umc.spring.domain.entity.Member;
import umc.spring.domain.entity.Review;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.service.MemberService.MemberCommandService;
import umc.spring.service.MemberService.MemberQueryService;
import umc.spring.validation.ValidPage;
import umc.spring.web.dto.member.MemberRequestDTO;
import umc.spring.web.dto.member.MemberResponseDTO;
import umc.spring.web.dto.store.MissionPreviewListDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberRestController {

    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;

    @PostMapping("/")
    public ApiResponse<MemberResponseDTO.JoinResultDTO> join(@RequestBody @Valid MemberRequestDTO.JoinDto request) {
        Member member = memberCommandService.joinMember(request);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
    }

    @GetMapping("/{memberId}/reviews")
    @Operation(summary = "내가 작성한 리뷰 목록 조회", description = "로그인한 사용자가 작성한 리뷰 목록을 조회합니다.")
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "page는 1 이상의 값이어야 함",
                    content = @Content(schema = @Schema(implementation = String.class))
            )
    })
    @Parameters({
            @Parameter(name = "memberId", description = "회원 ID"),
            @Parameter(name = "page", description = "1 이상의 페이지 번호 (쿼리 스트링)")
    })
    public ApiResponse<MemberResponseDTO.MyReviewListDTO> getMyReviews(
            @PathVariable Long memberId,
            @ValidPage @RequestParam int page
    ) {
        Page<Review> result = memberQueryService.getMyReviews(memberId, page - 1);
        return ApiResponse.onSuccess(MemberConverter.toMyReviewListDTO(result));
    }
    @GetMapping("/{memberId}/missions/in-progress")
    @Operation(summary = "내가 진행 중인 미션 목록 조회", description = "회원이 아직 완료하지 않은 미션 목록을 조회합니다.")
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "page는 1 이상의 값이어야 함")
    })
    @Parameters({
            @Parameter(name = "memberId", description = "회원 ID"),
            @Parameter(name = "page", description = "1 이상의 페이지 번호")
    })
    public ApiResponse<MissionPreviewListDTO> getInProgressMissions(
            @PathVariable Long memberId,
            @ValidPage @RequestParam int page
    ) {
        Page<MemberMission> missions = memberQueryService.getInProgressMissions(memberId, page - 1);
        return ApiResponse.onSuccess(MemberConverter.toMissionPreviewListDTOFromMemberMissions(missions));
    }

    @PatchMapping("/{memberId}/missions/{missionId}/complete")
    @Operation(summary = "진행 중인 미션 완료 처리", description = "특정 회원이 도전 중인 미션을 완료 상태로 변경합니다.")
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "도전하지 않은 미션"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "이미 완료된 미션")
    })
    @Parameters({
            @Parameter(name = "memberId", description = "회원 ID"),
            @Parameter(name = "missionId", description = "미션 ID")
    })
    public ApiResponse<String> completeMission(
            @PathVariable Long memberId,
            @PathVariable Long missionId
    ) {
        memberCommandService.completeMission(memberId, missionId);
        return ApiResponse.onSuccess("미션이 성공적으로 완료되었습니다.");
    }

}

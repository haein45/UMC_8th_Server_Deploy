package umc.spring.web.controller.store;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.entity.Mission;
import umc.spring.service.StoreService.StoreCommandService;
import umc.spring.service.StoreService.StoreQueryService;
import umc.spring.validation.ValidPage;
import umc.spring.web.dto.store.MissionPreviewListDTO;
import umc.spring.web.dto.store.StoreRequestDTO;
import umc.spring.web.dto.store.StoreResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreRestController {

    private final StoreCommandService storeCommandService;
    private final StoreQueryService storeQueryService;

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
}

package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.service.MissionService.MissionCommandService;
import umc.spring.web.dto.mission.MissionRequestDTO;
import umc.spring.web.dto.mission.MissionResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/store/{storeId}/mission")
public class MissionRestController {

    private final MissionCommandService missionCommandService;

    @PostMapping
    public MissionResponseDTO addMission(
            @PathVariable Long storeId,
            @RequestBody @Valid MissionRequestDTO request
    ) {
        return missionCommandService.createMission(storeId, request);
    }
}
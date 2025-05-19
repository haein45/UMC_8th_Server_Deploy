package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.spring.service.MemberMissionService.MemberMissionCommandService;
import umc.spring.web.dto.memberMission.MemberMissionRequestDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member/mission-challenge")
public class MemberMissionRestController {

    private final MemberMissionCommandService memberMissionCommandService;

    @PostMapping
    public ResponseEntity<String> challenge(@RequestBody @Valid MemberMissionRequestDTO requestDTO) {
        memberMissionCommandService.challengeMission(requestDTO.getMissionId());
        return ResponseEntity.ok("미션 도전이 완료되었습니다.");
    }
}


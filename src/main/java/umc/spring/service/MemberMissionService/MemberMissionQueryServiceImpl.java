package umc.spring.service.MemberMissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.repository.MemberMissionRepository.MemberMissionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberMissionQueryServiceImpl {

    private final MemberMissionRepository memberMissionRepository;

    public List<MemberMission> getMissionsByStatus(Long memberId, MissionStatus status, Long cursor, int limit) {
        return memberMissionRepository.findMissionsByStatusAndCursor(memberId, status, cursor, limit);
    }
}
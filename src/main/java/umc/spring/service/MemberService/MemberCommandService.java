package umc.spring.service.MemberService;

import org.springframework.web.multipart.MultipartFile;
import umc.spring.domain.entity.Member;
import umc.spring.web.dto.member.MemberRequestDTO;
import umc.spring.web.dto.member.MemberResponseDTO;

public interface MemberCommandService {
    Member joinMember(MemberRequestDTO.JoinDto request);
    void completeMission(Long memberId, Long missionId);
    MemberResponseDTO.LoginResultDTO loginMember(MemberRequestDTO.LoginRequestDTO request);

    String uploadProfileImage(Long memberId, MultipartFile profileImage);

    void deleteProfileImage(Long memberId);
}


package umc.spring.service.MemberService;

import umc.spring.domain.entity.Member;
import umc.spring.web.dto.member.MemberRequestDTO;

public interface MemberCommandService {
    Member joinMember(MemberRequestDTO.JoinDto request);
}


package umc.spring.converter.member;

import umc.spring.domain.entity.Member;
import umc.spring.domain.enums.Gender;
import umc.spring.web.dto.member.MemberRequestDTO;
import umc.spring.web.dto.member.MemberResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MemberConverter {

    // 응답용 DTO
    public static MemberResponseDTO.JoinResultDTO toJoinResultDTO(Member member){
        return MemberResponseDTO.JoinResultDTO.builder()
                .memberId(member.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    // 요청 DTO → Entity (회원가입)
    public static Member toMember(MemberRequestDTO.JoinDto request){
        Gender gender = switch (request.getGender()) {
            case 1 -> Gender.MALE;
            case 2 -> Gender.FEMALE;
            case 3 -> Gender.None;
            default -> Gender.None;
        };

        return Member.builder()
                .name(request.getName())
                .gender(gender)
                .address(request.getAddress())
                .specAddress(request.getSpecAddress())
                .memberPreferList(new ArrayList<>())  // 초기화 중요!
                .build();
    }
}

package umc.spring.converter.member;

import org.springframework.data.domain.Page;
import umc.spring.domain.entity.Member;
import umc.spring.domain.entity.Review;
import umc.spring.domain.enums.Gender;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.member.MemberRequestDTO;
import umc.spring.web.dto.member.MemberResponseDTO;
import umc.spring.web.dto.store.MissionPreviewDTO;
import umc.spring.web.dto.store.MissionPreviewListDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public static MemberResponseDTO.MyReviewDTO toMyReviewDTO(Review review) {
        return MemberResponseDTO.MyReviewDTO.builder()
                .storeName(review.getStore().getName())
                .score(review.getScore())
                .body(review.getBody())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static MemberResponseDTO.MyReviewListDTO toMyReviewListDTO(Page<Review> reviews) {
        List<MemberResponseDTO.MyReviewDTO> list = reviews.stream()
                .map(MemberConverter::toMyReviewDTO)
                .collect(Collectors.toList());

        return MemberResponseDTO.MyReviewListDTO.builder()
                .reviews(list)
                .totalPage(reviews.getTotalPages())
                .totalElements(reviews.getTotalElements())
                .isFirst(reviews.isFirst())
                .isLast(reviews.isLast())
                .build();
    }
    public static MissionPreviewDTO toMissionPreviewDTOFromMemberMission(MemberMission mm) {
        return MissionPreviewDTO.builder()
                .content(mm.getMission().getMissionSpec())
                .point(mm.getMission().getReward())
                .deadline(mm.getMission().getDeadline())
                .build();
    }

    public static MissionPreviewListDTO toMissionPreviewListDTOFromMemberMissions(Page<MemberMission> missions) {
        List<MissionPreviewDTO> list = missions.stream()
                .map(MemberConverter::toMissionPreviewDTOFromMemberMission)
                .collect(Collectors.toList());

        return MissionPreviewListDTO.builder()
                .missions(list)
                .totalPage(missions.getTotalPages())
                .totalElements(missions.getTotalElements())
                .isFirst(missions.isFirst())
                .isLast(missions.isLast())
                .build();
    }
}

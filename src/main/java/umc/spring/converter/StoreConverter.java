package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.entity.Mission;
import umc.spring.domain.entity.Region;
import umc.spring.domain.entity.Review;
import umc.spring.domain.entity.Store;
import umc.spring.web.dto.store.MissionPreviewDTO;
import umc.spring.web.dto.store.MissionPreviewListDTO;
import umc.spring.web.dto.store.StoreRequestDTO;
import umc.spring.web.dto.store.StoreResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class StoreConverter {
    public static Store toStore(StoreRequestDTO dto, Region region) {
        return Store.builder()
                .name(dto.getName())
                .address(dto.getAddress())
                .score(dto.getScore())
                .region(region)
                .build();
    }
    public static MissionPreviewDTO toMissionPreviewDTO(Mission mission) {
        return MissionPreviewDTO.builder()
                .content(mission.getMissionSpec())
                .point(mission.getReward())
                .deadline(mission.getDeadline())  // 필드 맞게 수정
                .build();
    }

    public static MissionPreviewListDTO toMissionPreviewListDTO(Page<Mission> missions) {
        List<MissionPreviewDTO> list = missions.stream()
                .map(StoreConverter::toMissionPreviewDTO)
                .collect(Collectors.toList());

        return MissionPreviewListDTO.builder()
                .missions(list)
                .totalPage(missions.getTotalPages())
                .totalElements(missions.getTotalElements())
                .isFirst(missions.isFirst())
                .isLast(missions.isLast())
                .build();
    }
    public static StoreResponseDTO.ReviewPreViewDTO reviewPreViewDTO(Review review) {
        return StoreResponseDTO.ReviewPreViewDTO.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getScore())
                .body(review.getBody())
                .createdAt(review.getCreatedAt().toLocalDate())
                .build();
    }

    public static StoreResponseDTO.ReviewPreViewListDTO reviewPreViewListDTO(Page<Review> reviewPage) {
        List<StoreResponseDTO.ReviewPreViewDTO> reviewList = reviewPage.stream()
                .map(StoreConverter::reviewPreViewDTO)
                .collect(Collectors.toList());

        return StoreResponseDTO.ReviewPreViewListDTO.builder()
                .reviewList(reviewList)
                .listSize(reviewList.size())
                .totalPage(reviewPage.getTotalPages())
                .totalElements(reviewPage.getTotalElements())
                .isFirst(reviewPage.isFirst())
                .isLast(reviewPage.isLast())
                .build();
    }

}


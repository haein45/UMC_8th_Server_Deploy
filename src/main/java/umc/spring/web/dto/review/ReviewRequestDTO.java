package umc.spring.web.dto.review;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.spring.validation.annotation.ExistStore;

@Getter
public class ReviewRequestDTO {

    @NotNull(message = "store_id는 필수입니다.")
    @ExistStore // 커스텀 어노테이션
    private Long storeId;

    @NotNull(message = "리뷰 제목은 필수입니다.")
    private String title;

    @Min(value = 1, message = "점수는 최소 1점 이상이어야 합니다.")
    @Max(value = 5, message = "점수는 최대 5점 이하여야 합니다.")
    private Float score;

    @NotNull(message = "리뷰 본문은 필수입니다.")
    private String body;
}
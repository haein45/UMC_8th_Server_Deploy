package umc.spring.web.dto.store;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import umc.spring.validation.annotation.ExistRegion;

@Getter
public class StoreRequestDTO {

    @NotNull(message = "지역 ID는 필수입니다.")
    @ExistRegion
    private Long regionId;

    @NotBlank(message = "가게 이름은 필수입니다.")
    private String name;

    @NotBlank(message = "가게 주소는 필수입니다.")
    private String address;

    @NotNull(message = "평점은 필수입니다.")
    private Float score;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewDTO {
        @Min(1)
        @Max(5)
        private int score;

        @NotBlank
        private String content;
    }
}



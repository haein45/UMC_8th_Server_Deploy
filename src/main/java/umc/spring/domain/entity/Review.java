package umc.spring.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.domain.base.BaseEntity;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String body;

    private Float score;

    @ManyToOne(fetch = FetchType.LAZY) //명시적 지연로딩 추가
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY) // fetch = FetchType.LAZY 추가
    @JoinColumn(name = "member_id")
    private Member member;

    //toString() 직접 정의하여 무한루프 방지 및 디버깅 편의성 확보
    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", body='" + body + '\'' +
                ", score=" + score +
                '}';
    }


}
package umc.spring.domain.entity;


import jakarta.persistence.*;
import lombok.*;
import umc.spring.domain.base.BaseEntity;
import umc.spring.domain.enums.Role;
import umc.spring.domain.enums.SocialType;
import umc.spring.domain.enums.Gender;
import umc.spring.domain.enums.Status;
import umc.spring.domain.mapping.MemberAgree;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.domain.mapping.MemberPrefer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 40)
    private String address;

    @Column(nullable = false, length = 40)
    private String specAddress;

    @Enumerated(EnumType.STRING) // Enum 값을 문자열로 저장 (e.g., 'MALE', 'FEMALE')
    @Column(columnDefinition = "VARCHAR(10)")
    private Gender gender;

    @Enumerated(EnumType.STRING) // Enum 값을 문자열로 저장 (e.g., 'ACTIVE', 'INACTIVE')
    @Column(columnDefinition = "VARCHAR(15) DEFAULT 'ACTIVE'")
    private Status status;

    @Enumerated(EnumType.STRING) // Enum 값을 문자열로 저장 (e.g., 'GOOGLE', 'FACEBOOK')
    @Column(columnDefinition = "VARCHAR(20)")
    private SocialType socialType;

    private LocalDate inactiveDate;

//    @Column(nullable = false, length = 50)
//    private String email;

    private Integer point;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberAgree> memberAgreeList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberPrefer> memberPreferList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberMission> memberMissionList = new ArrayList<>();

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "profile_image_url")
    private String profileImageUrl;

    public void updateProfileImageUrl(String imageUrl) {
        this.profileImageUrl = imageUrl;
    }

    public String extractKeyFromImageUrl(String imageUrl) {
        // URL에서 key만 추출 (예: https://bucket.s3.region.amazonaws.com/folder/filename.jpg → folder/filename.jpg)
        // AWS S3 URL은 항상 "https://...amazonaws.com/"까지가 공통 prefix임
        int index = imageUrl.indexOf(".amazonaws.com/");
        if (index == -1) return null; // 예외 처리
        return imageUrl.substring(index + ".amazonaws.com/".length() + 1); // +1은 슬래시
    }


    public void encodePassword(String password) {
        this.password = password;
    }
}

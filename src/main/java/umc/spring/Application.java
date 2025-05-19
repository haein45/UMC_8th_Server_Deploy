package umc.spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import umc.spring.domain.enums.MissionStatus;

@SpringBootApplication
@EnableJpaAuditing
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	//
//	@Bean
//	public CommandLineRunner run(ApplicationContext context) {
//		return args -> {
//			StoreQueryService storeService = context.getBean(StoreQueryService.class);
//
//			// 파라미터 값 설정
//			String name = "요아정";
//			Float score = 4.0f;
//
//			// 쿼리 메서드 호출 및 쿼리 문자열과 파라미터 출력
//			System.out.println("Executing findStoresByNameAndScore with parameters:");
//			System.out.println("Name: " + name);
//			System.out.println("Score: " + score);
//
//			storeService.findStoresByNameAndScore(name, score)
//					.forEach(System.out::println);
//		};
//	}}
	@Bean
	public CommandLineRunner testMemberMissions(ApplicationContext context) {
		return args -> {
			var missionService = context.getBean(umc.spring.service.MemberMissionService.MemberMissionQueryServiceImpl.class);

			Long memberId = 1L;
			MissionStatus status = MissionStatus.진행중;
			Long cursor = Long.MAX_VALUE;
			int limit = 10;

			var missions = missionService.getMissionsByStatus(memberId, status, cursor, limit);

			missions.forEach(m -> {
				System.out.println("🟢 미션 ID: " + m.getMission().getId());
				System.out.println("🏪 스토어 이름: " + m.getMission().getStore().getName());
				System.out.println("💰 보상: " + m.getMission().getReward());
				System.out.println("📄 미션 설명: " + m.getMission().getMissionSpec());
				System.out.println("⏳ 상태: " + m.getStatus());
				System.out.println("------------");
			});
		};
	}
//	@Bean
//	public CommandLineRunner testCreateReview(ApplicationContext context) {
//		return args -> {
//			var reviewService = context.getBean(umc.spring.service.ReviewService.ReviewCommandService.class);
//
//			Long memberId = 1L;
//			Long storeId = 1L;
//			String body = "이 가게 정말 맛있어요!";
//			Float score = 4.8f;
//
//			Review review = reviewService.createReview(memberId, storeId, body, score);
//
//			System.out.println("🎉 리뷰 저장 성공!");
//			System.out.println("내용: " + review.getBody());
//			System.out.println("평점: " + review.getScore());
//			System.out.println("작성자: " + review.getMember().getName());
//			System.out.println("가게명: " + review.getStore().getName());
//		};
//	}

}


package umc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}

package umc.spring.repository.StoreRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.entity.Store; // 패키지 경로는 Store 엔티티 위치에 따라 조정

public interface StoreRepository extends JpaRepository<Store, Long>, StoreRepositoryCustom {
}

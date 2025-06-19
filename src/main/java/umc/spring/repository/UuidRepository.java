package umc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.aws.s3.Uuid;

public interface UuidRepository extends JpaRepository<Uuid, Long> {
}

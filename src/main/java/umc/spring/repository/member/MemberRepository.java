package umc.spring.repository.member;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.entity.Member;
import umc.spring.repository.member.MemberRepositoryCustom;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {
    Optional<Member> findByEmail(String email);
}

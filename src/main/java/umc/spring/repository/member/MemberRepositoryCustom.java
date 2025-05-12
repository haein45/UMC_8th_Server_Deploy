package umc.spring.repository.member;

import umc.spring.domain.entity.Member;

public interface MemberRepositoryCustom {
    Member findByIdUsingQueryDSL(Long id);
}
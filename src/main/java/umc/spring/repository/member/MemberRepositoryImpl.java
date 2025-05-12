package umc.spring.repository.member;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.spring.domain.entity.Member;
import umc.spring.domain.entity.QMember;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    QMember member = QMember.member;

    @Override
    public Member findByIdUsingQueryDSL(Long id) {
        return queryFactory
                .selectFrom(member)
                .where(member.id.eq(id))
                .fetchOne();
    }
}

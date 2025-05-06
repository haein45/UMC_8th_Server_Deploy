package umc.spring.repository.MemberMissionRepository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.spring.domain.entity.QMission;
import umc.spring.domain.entity.QStore;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.domain.mapping.QMemberMission;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberMissionRepositoryImpl implements MemberMissionRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    QMemberMission memberMission = QMemberMission.memberMission;
    QMission mission = QMission.mission;
    QStore store = QStore.store;

    @Override
    public List<MemberMission> findMissionsByStatusAndCursor(Long memberId, MissionStatus status, Long cursor, int limit) {
        return queryFactory
                .selectFrom(memberMission)
                .join(memberMission.mission, mission).fetchJoin()
                .join(mission.store, store).fetchJoin()
                .where(
                        memberMission.member.id.eq(memberId),
                        memberMission.status.eq(status),
                        mission.id.lt(cursor)
                )
                .orderBy(mission.id.desc())
                .limit(limit)
                .fetch();
    }

}
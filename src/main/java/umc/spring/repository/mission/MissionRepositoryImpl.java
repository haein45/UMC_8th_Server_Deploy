package umc.spring.repository.mission;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.spring.domain.entity.Mission;
import umc.spring.domain.entity.QMission;
import umc.spring.domain.entity.QRegion;
import umc.spring.domain.entity.QStore;
import umc.spring.domain.mapping.QMemberMission;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MissionRepositoryImpl implements MissionRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    QMission mission = QMission.mission;
    QStore store = QStore.store;
    QRegion region = QRegion.region;
    QMemberMission memberMission = QMemberMission.memberMission;

    @Override
    public List<Mission> findAvailableMissions(Long memberId, String regionName, Long cursor, int limit) {
        return queryFactory
                .selectFrom(mission)
                .join(mission.store, store).fetchJoin()
                .join(store.region, region).fetchJoin()
                .where(
                        region.name.eq(regionName),
                        mission.deadline.gt(java.time.LocalDate.now()),
                        mission.id.lt(cursor),
                        mission.id.notIn(
                                queryFactory
                                        .select(memberMission.mission.id)
                                        .from(memberMission)
                                        .where(memberMission.member.id.eq(memberId))
                        )
                )
                .orderBy(mission.id.desc())
                .limit(limit)
                .fetch();
    }
}

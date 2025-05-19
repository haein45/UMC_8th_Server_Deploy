package umc.spring.service.MemberService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.FoodCategoryHandler;
import umc.spring.converter.member.MemberPreferConverter;
import umc.spring.domain.entity.FoodCategory;
import umc.spring.domain.entity.Member;
import umc.spring.domain.mapping.MemberPrefer;
import umc.spring.repository.FoodCategoryRepository;
import umc.spring.repository.member.MemberRepository;
import umc.spring.web.dto.member.MemberRequestDTO;
import umc.spring.converter.member.MemberConverter;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;
    private final FoodCategoryRepository foodCategoryRepository;

    @Override
    public Member joinMember(MemberRequestDTO.JoinDto request) {
        // DTO → Entity
        Member newMember = MemberConverter.toMember(request);

        // Category ID → Category Entity
        List<FoodCategory> foodCategoryList = request.getPreferCategory().stream()
                .map(id -> foodCategoryRepository.findById(id)
                        .orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND)))
                .collect(Collectors.toList());

        // FoodCategory → MemberPrefer
        List<MemberPrefer> memberPreferList = MemberPreferConverter.toMemberPreferList(foodCategoryList);

        // 양방향 연관관계 설정
        memberPreferList.forEach(prefer -> prefer.setMember(newMember));

        return memberRepository.save(newMember);
    }
}


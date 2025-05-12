package umc.spring.repository.MemberService;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.domain.entity.Member;
import umc.spring.repository.member.MemberRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberQueryService {

    private final MemberRepository memberRepository;

    public Member getMyPageInfo(Long memberId) {
        Member member = memberRepository.findByIdUsingQueryDSL(memberId);
        if (member == null) {
            throw new RuntimeException("해당 회원을 찾을 수 없습니다.");
        }
        return member;
    }
}
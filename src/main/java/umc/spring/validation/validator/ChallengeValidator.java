package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.repository.MemberMissionRepository.MemberMissionRepository;
import umc.spring.validation.annotation.NotDuplicatedChallenge;

@Component
@RequiredArgsConstructor
public class ChallengeValidator implements ConstraintValidator<NotDuplicatedChallenge, Long> {

    private final MemberMissionRepository memberMissionRepository;

    @Override
    public boolean isValid(Long missionId, ConstraintValidatorContext context) {
        Long hardCodedMemberId = 1L; // 하드코딩된 memberId

        boolean exists = memberMissionRepository.existsByMemberIdAndMissionId(hardCodedMemberId, missionId);

        if (exists) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("이미 도전한 미션입니다.").addConstraintViolation();
            return false;
        }
        return true;
    }
}

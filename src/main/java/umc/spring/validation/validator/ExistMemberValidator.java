package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.repository.member.MemberRepository;

@Component
@RequiredArgsConstructor
public class ExistMemberValidator implements ConstraintValidator<umc.spring.validation.annotation.ExistMember, Long> {

    private final MemberRepository memberRepository;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value == null) return false;
        return memberRepository.existsById(value);
    }
}

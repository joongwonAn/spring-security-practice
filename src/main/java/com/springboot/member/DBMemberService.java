package com.springboot.member;

import com.springboot.exception.BusinessLogicException;
import com.springboot.exception.ExceptionCode;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class DBMemberService implements MemberService {
    private MemberRepository memberRepository;
    private PasswordEncoder passwordEncoder;

    public DBMemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Member createMember(Member member) {
        verifyExistsEmail(member.getEmail());
        String encodedPassword = passwordEncoder.encode(member.getPassword());

        member.setPassword(encodedPassword);

        Member savedMember = memberRepository.save(member);
        return savedMember;
    }

    private final void verifyExistsEmail(String email) {
        memberRepository.findByEmail(email)
                .ifPresent(exists -> new BusinessLogicException(ExceptionCode.MEMBER_EXISTS));
    }
}

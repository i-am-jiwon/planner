package com.td.planner.domain.member.member.service;

import com.td.planner.domain.member.member.entity.Member;
import com.td.planner.domain.member.member.repository.MemberRepository;
import com.td.planner.global.rsData.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public RsData<Member> join(String email, String username, String password, String joinType){
        Member member = Member.builder()
                .username(username)
                .email(email)
                .password(passwordEncoder.encode(password))
//              .refreshToken(authTokenService.genRefreshToken())
                .joinType(joinType)
                .build();
        memberRepository.save(member);

        return RsData.of("%s님 환영합니다. 회원가입이 완료되었습니다. 로그인 후 이용해주세요.".formatted(member.getUsername()), member);
    }

}

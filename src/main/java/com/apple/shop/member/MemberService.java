package com.apple.shop.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void addMember(String username, String password, String displayName) throws Exception {
        Member member = new Member();
        var result = memberRepository.findByUsername(username);
        if(result.isPresent()){
            throw new Exception("존재하는 아이디");
        }
        if(username.length() < 3 || password.length() < 4){
            throw new Exception("짧음");
        }
        String hashPassword = passwordEncoder.encode(password);
        member.setUsername(username);
        member.setPassword(hashPassword);
        member.setDisplayName(displayName);
        memberRepository.save(member);
    }
}

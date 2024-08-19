package com.apple.shop.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/signup")
    String siginup (){
        return "signup.html";
    }

    @PostMapping("/member")
    String postMember(String username, String password, String displayName) throws Exception {
        memberService.addMember(username, password, displayName);

        return "redirect:/list";
    }

    @GetMapping("/login")
    public String login(){
        return "login.html";
    }

    @GetMapping("/my-page")
    public String myPage(Authentication auth){
        if(auth.isAuthenticated()){}
        return "mypage.html";
    }
}

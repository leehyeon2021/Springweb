package springweb.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springweb.member.service.MemberService;

@RestController @RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {
    private MemberService memberService;



}

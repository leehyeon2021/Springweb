package springweb.member.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springweb.member.dto.MemberDto;
import springweb.member.service.MemberService;

@RestController @RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {
    private final MemberService memberService;

    // 1. 회원가입
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody MemberDto memberDto){
        return ResponseEntity.ok(memberService.signup(memberDto));
    }

    // 2. 로그인 - 세션 안 한 버전
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody MemberDto loginDto){
//        return ResponseEntity.ok(memberService.login(loginDto));
//    }

    // 2. 로그인 (post = select = find) - 세션 안 한 버전
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody MemberDto loginDto , HttpSession session){
        // 1. 입력받은 아이디/비밀번호를 서비스에게 보낸다.
        boolean result = memberService.login( loginDto );
        // 2. 만약에 로그인 성공이면 세션 부여
        if(result) {
            // 1. 매개변수에 HttpSession session 받는다.
            // 2. 로그인 성공한 회원의 아이디를 세션 객체 내 저장. `.setAttribute( "속성명" , 속성값 );`
            session.setAttribute("loginMid", loginDto.getMid());
        }
        // 3. 아니면 실패
        return ResponseEntity.ok( result );
    }

    // 3. 로그아웃 (주로 Get)
    @GetMapping("/logout")
    public ResponseEntity<?> logout( HttpSession session ){
        // 1. 매개변수에 HttpSession session 받는다.
        // 2. 특정한 속성명으로 세션 객체 내 속성 삭제(=로그아웃). `session.removeAttribute( "삭제할속성명" );`
        session.removeAttribute( "loginMid" );
        // 3. 반환
        return ResponseEntity.ok( true );
    }
    // 로그인은 `.setAttribute()` , 로그아웃은 `.removeAttribute()` , 꺼내올 때는 `.getAttribute()`

    // 4. 마이페이지 (GET==(현재 로그인 된 회원정보 조회 == 세션에 저장된 정보로 조회))
    @GetMapping("/my/info")
    public ResponseEntity<?> myInfo( HttpSession session ){
        // 1. 로그인 상태 꺼내기. **!주의: 모든 세션 객체 내 속성값은 Object 타입.**
        Object obj = session.getAttribute("loginMid");
        // 2. 로그인 상태가 존재하지 않으면 비로그인 상태
        if( obj == null ){ return ResponseEntity.ok(false);}
        // 3. 로그인 상태이면 다운캐스팅(Object -> String)    // 업캐스팅: 55번줄에 "loginMid"String값들이 Object로 ㄱㄱ
        String loginMid = (String)obj;
        // 4. 로그인된 mid로 서비스에게 전달, 로그인된 mid의 dto 반환.
        return ResponseEntity.ok(memberService.myInfo(loginMid));
    }
}

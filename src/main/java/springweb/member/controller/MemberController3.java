package springweb.member.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springweb.member.dto.MemberDto;
import springweb.member.service.JWTService;
import springweb.member.service.MemberService;

// 쿠키! (토큰+쿠키. LocalStorage 말고 Cookies 사용)

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member3")
@CrossOrigin( value = "http://localhost:5173", allowCredentials = "true" ) // 쿠키가 오갈 수 있도록 함
public class MemberController3 {
    private final MemberService memberService;
    private final JWTService jwtService;

    // 1. 회원가입 == 이전과 동일하게 세션,토큰,쿠키와 관련 없으므로 이전 것 사용

    // 2. 로그인 = 세션 방식-->토큰+쿠키 방식 변경
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody MemberDto loginDto,
                                   HttpServletResponse response ){ // HttpServletResponse 응답객체

        boolean result = memberService.login( loginDto );
        System.out.println(result);

        // 1. 만약 로그인 성공이면
        if(result) {
            // 2. 로그인 성공한 정보(아이디)를 토큰에 저장
            String token = jwtService.createToken(loginDto.getMid());
            // ===================== 추가 =====================
            // 3. 쿠키에 토큰 담아서 응답하기

            // 1) 쿠키에 토큰 담기: `new Cookie( "속성명" , 값 );`
            Cookie cookie = new Cookie( "token" , token );

            // 2) 쿠키 옵션 ⭐ (웹게임에서 많이 사용)
            cookie.setHttpOnly( true ); // .setHttpOnly( true ): 쿠키 접근 방법. true이면 JS가 접근을 못 한다. (개발자도구에서 수정 불가)
            cookie.setSecure( false ); // .setSecure( false ): true이면 https만 접근 가능 (http는 불가)
            cookie.setPath( "/" ); // .setPath( ): 쿠키 접근하는 경로. "/": 전체 경로
            // cookie.setMaxAge( 초 ): 쿠키 유지 시간

            // 3) 쿠키 응답하기: response.addCookie( 쿠키 객체 );
            response.addCookie( cookie );

            return ResponseEntity.ok(true);
        }else { return ResponseEntity.ok( false );}
    }


    // 3. 로그아웃 = 세션 방식-->토큰+쿠키 방식 변경

    // 4. 마이페이지 = 세션 방식-->토큰+쿠키 방식 변경
}

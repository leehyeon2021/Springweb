package springweb.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springweb.member.dto.MemberDto;
import springweb.member.service.JWTService;
import springweb.member.service.MemberService;

// day015(JWT) 참고

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member2")

// 도메인 열어주기 (Authorization 허용)
@CrossOrigin(value = "http://localhost:5173", exposedHeaders = "Authorization")

public class MemberController2 {
    private final MemberService memberService;
    private final JWTService jwtService;

    // 1. 회원가입 == 세션방식과 동일하게 세션,토큰과 관련 없으므로 생략.

    // 2. 로그인 = 세션 방식-->토큰 방식으로 변경하기
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody MemberDto loginDto){
        System.out.println("데이터: "+loginDto.toString());
        boolean result = memberService.login( loginDto );
        System.out.println(result);
        // 1. 만약에 로그인 성공이면 토큰 부여
        if(result){
            // 2. 로그인 성공한 정보 (아이디)
            String token = jwtService.createToken( loginDto.getMid() ); // 세션은 알고리즘 없이 그냥 저장.
            return ResponseEntity.ok()
                    // 3. 토큰(jwt)은 주로 세션과 다르게 서버에 저장되지 않고 클라이언트에 저장한다.
                    .header( "Authorization" , "Bearer "+token) // HTTP 통신의 부가정보를 담는 구역 (주로 인증 정보를 포함한다.)
                    // 클라이언트에게서 헤더에 발급받은 jwt토큰을 반환한다. Bearer token (띄어쓰기 주의)
                    .body(token); // 긍정 의미
        }
        // 3. 아니면 실패
        return ResponseEntity.ok( false );
    }

    // 3. 로그아웃 = 세션 방식-->토큰 방식으로 변경하기
        // 서버가 token 저장하고 있지 않기 때문에 통신이 필요없다.
        // 즉] 클라이언트 측에서 token 제거하면 된다.

    // 4. 마이페이지 = 세션 방식-->토큰 방식으로 변경하기
    @GetMapping("/my/info")
    public ResponseEntity<?> myInfo( @RequestHeader("Authorization") String token ) {
        // `@RequestHeader`: HTTP 요청의 header 정보 매핑.
            // body나 param에 받아도 되긴 하지만, 인증 정보는 Header로 주고받는 것이 좋다. (보안상)
        // 1. @RequestHeader("Authorization") String token 매개변수로 받기
        // 2. 만약에 헤더가 없거나 토큰이 없으면 비로그인
        if(token==null || !token.startsWith("Bearer")){
            return ResponseEntity.ok(false);
        }
        // 3. 토큰만 추출. `문자열.replace( "기존문자" , "새로운문자" )
        token = token.replace( "Bearer " , "" ); // Bearer 없애기(공백 포함해서): `Bearer eyJhbGciOiJIU...` -> `eyJhbGciOiJIU...`
        // 4. 토큰에서 값(클레임) 추출
        String mid = jwtService.getClaim(token);
        if( mid == null )return ResponseEntity.ok(false);
        // 5. 토큰에서 꺼낸 값(mid)으로 회원정보 요청하기
        return ResponseEntity.ok( memberService.myInfo(mid) );

    }
}

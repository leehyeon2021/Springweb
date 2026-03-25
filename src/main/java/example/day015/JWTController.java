package example.day015;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// java/springweb의 MemberController2와 JWTService, BoardController 등등 참고

@RestController @RequiredArgsConstructor
@RequestMapping("/api/jwt")
public class JWTController {
    private final JWTService jwtService;

    // 1. JWT라는 토큰 생성 == 데이터를 암호화 한다. (주로 로그인정보 (vs. 세션(HTTP)))
    // http://localhost:8080/api/jwt/create?data=banana
    @GetMapping("/create") // banana 보냄-> eyJhbGciOiJIUzI1NiJ9.eyJkYXRhIjoiYmFuYW5hIiwiaWF0IjoxNzc0MzI2MDgxLCJleHAiOjE3NzQzMjYxMDF9.aNRkO6IwzVYSgaYzU5kbQ2QpQMW0o_4PGsekYcuUPtI
    public ResponseEntity<?> 토큰생성(@RequestParam String data){
        return ResponseEntity.ok( jwtService.토큰생성( data ) );
    }

    // 2. JWT 토큰 값 추출 == 데이터 암호화된 JWT 토큰을 다시 평문으로 바꾸기
    // http://localhost:8080/api/jwt/read?token=위에서 준 토큰 값 넣으면 banana(data값)나옴
    @GetMapping("/read")
    public ResponseEntity<?> 값추출(@RequestParam String token){
        return ResponseEntity.ok(jwtService.값추출( token ));
    }
}

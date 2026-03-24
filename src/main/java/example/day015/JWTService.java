package example.day015;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service @RequiredArgsConstructor
public class JWTService {

    // * 비밀키 생성
        // * 내가 만든 임의의 값으로 토큰에 사용되는 암호화 계산식의 비밀번호 만들기 (32글자 이상)
    private final String 비밀번호 = "12345678912345678912345678912345"; // 내가 만든 32글자 이상 키.
        // * 내가 만든 임의의 값(비밀번호) 해시값으로 변환
    private final Key 비밀키 = Keys.hmacShaKeyFor( 비밀번호.getBytes() );

    // 1. JWT 토큰 생성
    public String 토큰생성( String data ){
        String token = Jwts.builder()                   // 토큰 생성 시작 (시작)
                .claim( "data" , data )              // 토큰에 저장할 자료를 key와 value로 대입한다. `.claim( "key" , value )`
                .setIssuedAt( new Date() )              // 토큰 발급 날짜/시간
                // .setExpiration( new Date( System.currentTimeMillis()+1000 * 초단위 ) ) +1000*20 <- 이건 20초
                .setExpiration(new Date(System.currentTimeMillis()+1000*60)) // 토큰 유지/유효 시간. 초 단위로 들어감.
                // `.signWith( 비밀키 , 암호화알고리즘 )` 비밀키는 미리 만들어놔야함
                .signWith( 비밀키  , SignatureAlgorithm.HS256 ) // 최종 토큰 암호화: HS256 알고리즘을 적용한다.
                .compact();                             // 토큰 최종 문자열로 반환 (끝)
        return token;
    }

    // 2. JWT 토큰 값 추출
    public String 값추출( String token ){
        try {
            Claims claims = Jwts.parser() // 파싱/ 가져온다는 뜻
                    .setSigningKey(비밀키) // 서명 검증에 필요한 비밀키 대입
                    .build() // 비밀키 확인
                    .parseClaimsJws(token) // 검증할 토큰 대입한다.
                    .getBody(); // 인증 성공시 클레임(내용물)을 가져온다.

            return (String) claims.get("data"); // 저장된 값은 무조건 Object
        }catch (Exception e){System.out.println(e);}
        return null;
    }
}
/*
## JWT (Json Web Token(징표))
1. 정의
: JSON형식의 데이터를 사용하기 위한 토큰 기반의 인증 방식
   - 간단하게: JSON을 데이터 형식으로 암호화.
2. 목적
: 웹/앱에서 인증과 권한부여/확인할 때 사용(클라이언트) ( VS. 세션(서버) )
   - 세션: 안전함-암호화. 그러나 서버 과부화 우려.
   - JWT: 안전하진 않음
3. 사용법
   1. 설치
      - implementation 'io.jsonwebtoken:jjwt-api:0.12.6'
      - runtimeOnly 'io.jsonwebtoken:jjwt-impl:0.12.6'
      - runtimeOnly 'io.jsonwebtoken:jjwt-jackson:0.12.6'
*/

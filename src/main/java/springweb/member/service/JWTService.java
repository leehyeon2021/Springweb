package springweb.member.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
public class JWTService {

    // 로그인 할 거임

    // 0. 비밀키 정의 - 개발자가 임의로 32자 이상으로 지정
    private String secret = "12341234123412341234123412341234";
    private Key secretKey = Keys.hmacShaKeyFor( secret.getBytes( StandardCharsets.UTF_8 ) );
        // SHA-25엥
        // .getBytes( StandardCharsets.UTF_8 ) -> 한글 넣을 거면 UTF-8 넣어주기.

    // 1. 토큰 발급 - 특정한 자료를 사람이 이해하기 어려운 자료로 변경
    public String createToken( String mid ){
        String token = Jwts.builder() // 토큰객체 생성 빌더 시작
                .claim( "mid", mid ) // key와 value 쌍으로 토큰에 저장할 값
                // .claim( "mrole", mrole ) 권한 같은 거 넣으려면 이렇게 여러 개 넣기 가능.
                .setIssuedAt( new Date() ) // 토큰 발급 날짜/시간       // new Date()는 현재시간. 현재시간 기준.
                .setExpiration( new Date(System.currentTimeMillis()*1000*60*60*24) ) // 토큰 유지/유효 시간.
                .signWith( secretKey , SignatureAlgorithm.HS256 ) // 토큰에 비밀키 넣고 서명 알고리즘 (HS512)
                .compact(); // 토큰객체 생성 빌더 종료
        return token;
    }

    // 2. 토큰의 클레임(내용물) 추출
    public String getClaim( String token ){ // 토큰 발급에서 String으로 했으니 String으로.
        try{
            Claims claims = Jwts.parser()
                    .setSigningKey( secretKey ) // 비밀키 대입
                    .build() // 비밀키 확인. 비밀키 일치하지 않으면 예외 발생
                    .parseClaimsJws( token ) // 서명 확인 필요한 토큰 대입
                    .getBody(); // 서명 확인한 토큰 내 클레임(내용물) 반환. 없으면 예외 발생
            Object obj = claims.get( "mid" ); // 클레임(내용물) 값은 무조건 Object
            return (String)obj;
        }catch (Exception e){System.out.println(e);}
        return null; // 토큰이 없거나 유효하지 않음
    }
}

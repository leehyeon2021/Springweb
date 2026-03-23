package example.day014;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequiredArgsConstructor @RequestMapping("/api/session")
public class SessionController {

    // 1. 세션 객체 내 값 저장 (=로그인)
    @PostMapping("") // http://localhost:8080/api/session?data=세션테스트
    public ResponseEntity<?> test1(@RequestParam String data, HttpServletRequest request){

        // 1. HttpServletRequest: HTTP 요청 정보를 담고 있는 객체
        System.out.println( request.getRemoteAddr() );  // 1) 요청한 클라이언트(사용자) IP 주소 얻기( 로그/위치추적/조회수 등 )      // 0:0:0:0:0:0:0:1
                                                            // IP주소로 위치 추적 가능. 사용자 IP로 한 사람 조회수 중복 안 되게.
        System.out.println( request.getHeader("User-Agent") ); // 2) 요청한 클라이언트의 브라우저 정보(기기정보 같은 거)     // null (탈란드로 해서 브라우저 정보 없음)
        System.out.println( request.getSession() );     // 3) 요청한 클라이언트의 세션 객체 정보. **각 브라우저 마다 할당됨.**      // org.apache.catalina.session.StandardSessionFacade@1bbc1b32

        // 2. 세션 객체
        HttpSession session = request.getSession();
        System.out.println( session.getId() );           // 1) 세션의 식별 번호 반환. 브라우저/디바이스 마다 할당.
        System.out.println( session.getCreationTime() ); // 2) 세션의 최초 생성 시간
        System.out.println( session.getLastAccessedTime() );    // 3) 세션의 마지막 접근 시간
        System.out.println( session.getMaxInactiveInterval() ); // 4) 세션의 최대 유효시간(초)

        // 3. 세션 객체 내 저장 == 로그인
        session.setAttribute( "data", data );               // 1) 세션 객체 내 값을 key:value로 저장.
        System.out.println( session.getAttribute("data") ); // 2) 세션 객체 내 값을 key로 호출.

        return ResponseEntity.ok(true);
    }

    // 2. 세션 객체 내 값 호출 (=로그인의회원정보(마이페이지))

    // 3. 세션 객체 내 값 제거 (=로그아웃)

    // +) 세션 객체 내 값 수정: 값 덮어쓰기만 하면 된대

}
/*
### 톰캣 세션
1. 정의
: 톰캣(서버프로그램) 내 데이터를 **저장**하고 관리할 수 있게 메모리/객체 제공
2. 목적
   1. 상태/값 관리
   2. 인증/권한
   3. 효율적인 메모리
3. 사용처
   1. 로그인 상태 구현
      - 세션으로 하거나 토큰(JWT)으로 하는 경우가 존재.
      - 서버 내부에 저장=세션(암호화x) vs. 클라이언트(서버 외부)에 저장=JWT(암호화o)
      - 세션은 서버(보안안전/과부화위험)에, 토큰은 클라이언트(사용자-외부) 쪽에 저장.
   2. 비회원제의 장바구니
   3. 실시간 처리에서 데이터 저장
   - 주로 로그인 상태 저장할 때 많이 사용한다.
4. 특징
: 브라우저(크롬/엣지/사파리/TV 등) 마다 별도의 세션 객체가 할당된다.
   - 예시) (같은 계정 로그인했다고 치면) 크롬에서 로그아웃해도 엣지에서 로그아웃 안 됨

HttpServletRequest
- 서블릿(Servlet): WAS(톰캣) 서버 내 웹 기술이 가능하게 하는 JAVA의 HTTP 통신 클래스 객체
- 주요 메소드
   1. `.getRemoteAddr()`: 요청한 클라이언트의 IP 반환.
   2. `.getHeader("User-Agent")`: 요청한 클라이언트의 브라우저 정보 반환.
      - 넷플릭스의 기기 정보 확인 후 로그아웃 할 수 있게 해주는 기능 중: 기기 정보 확인
   3. `.getSession()`: 요청한 클라이언트의 세션 객체를 반환. **브라우저 마다**

*/
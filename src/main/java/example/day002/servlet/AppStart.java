package example.day002.servlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.servlet.context.ServletComponentScan;

@SpringBootApplication // 스프링부트 환경 의존성(미리 만들어진 코드) 주입
/* 모든 코드들은 ctrl+클릭 하면 해당 라이브러리로 이동.
   - 배울 핵심 @: @SpringBootConfiguration, @EnableAutoConfiguration, @ComponentScan
   * WAS(웹 서버 v.11) 환경 설정이 포함되어 있다. (AppStart 실행하면 나오는 것들 중 'main] o.s.boot.tomcat.TomcatWebServer : Tomcat initialized with port 8080 (http)'. 톰캣 설정 들어감.)
        -> HTTP(Hypertext Transfer Protocol): localhost: 8080 vs. ip(cmd에서 ipconfig 하면 알 수 있음)
 */
@ServletComponentScan // 서블릿 클래스를 스캔하여 등록한다.
public class AppStart {
    public static void main(String[] args) {
        SpringApplication.run( AppStart.class ); // SpringBoot로 부터 받은 애를 실행
        /*SpringApplication.run( 현재클래스 )
        : 스프링 실행 함수
        헌재클래스는 @SpringBootApplicateion 의존성 주입 받은 상태.
        즉] 스프링 환경 세팅이 AppStart에 연결된 상태이므로 AppStart 클래스 실행
        클래스명.class : 클래스 정보(멤버/함수/상속/구현/생성자 등) 호출          */
        System.out.println(AppStart.class); // AppStart.class = class example.day002.servlet.AppStart
    }
}

// 탈란드 API Content-type을 따로 설정하지 않아서(ppt참고) response는 메모장 형식으로 다운 받아서 확인해야 함. (하단 BODY의 Click here)
package example.day002.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


// REST란? HTTP GET/POST/PUT/DELETE 활용하여 통신
// Controller란? view(사용자/클라이언트)와 model(dao) 사이의 통신(HTTP) 중계를 하는 중간 역할.
// Component란? 스프링이 해당 클래스를 이해할 수 있게 스프링 컨테이너에 빈(객체) 정보 등록. 싱글톤 대신! 싱글톤 생성하지 않아도 된다.
// @Component // Bean 등록.
// 근데 HTTP 기능까지 포함된 어노테이션이 있음. 서블릿 대신? 서블릿이 포함되어 있다. (스프링이 서블릿을 대신 써준다. /servlet 패키지에서 연습한 방식은 이제 안 함.)
@Controller // Bean 등록 + HTTP 통신 중계.
public class RestController1 {
    // 1. @Controller(Component가 포함됨) 이므로 싱글톤 생략.
    // 2. HTTP 기능(방법/함수/메소드/행위)
    //   - @XXXMapping : 클라이언트가 요청한 HTTP 메소드와 매핑(연결)하는 어노테이션
    //   1. POST
    @PostMapping
    public void 등록하기(){System.out.println("RestController1.등록하기");}

    //   2. GET
    @GetMapping
    public void 조회하기(){System.out.println("RestController1.조회하기");}

    //   3. PUT
    @PutMapping
    public void 수정하기(){System.out.println("RestController1.수정하기");}

    //   4. DELETE
    @DeleteMapping
    public void 삭제하기(){System.out.println("RestController1.삭제하기");}


}

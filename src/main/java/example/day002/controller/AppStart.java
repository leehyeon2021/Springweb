package example.day002.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @ComponentScan: 스프링이 시작될 때, 스프링 컨테이너에 등록할 빈(@Component)들을 동일/하위 패키지에서 찾아 등록한다. (그러니 AppStart는 가장 상위에 두기.)
// @Component에 포함된 것 : @Controller , @Service , @RestController , @Repository(저장소. DAO 대신.) 등등. 몇몇 어노테이션들은 내장되어 있다.
public class AppStart {
    public static void main(String[] args) {
        SpringApplication.run( AppStart.class );
    }
}

// 주소 지정 안 해서 ERROR 나긴 하는데
// RestController1.등록하기 라고 뜸. 잘 됨!
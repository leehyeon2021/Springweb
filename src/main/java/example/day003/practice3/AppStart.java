package example.day003.practice3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppStart {
    public static void main(String[] args) {
        SpringApplication.run( AppStart.class );
    }
}

/*
1. day003 -> practice3 폴더 생성
2. AppStart 클래스에서 스프링 환경 **스프링 실행되는 환경부터**
3. Controller 클래스 준비
    @RestController
    @RequestMapping("/attendance")
4. AttendanceDto 클래스 준비
 */

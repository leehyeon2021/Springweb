package example.day001;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // '의존성 주입'
public class AppStart {
    public static void main(String[] args) {
        // SpringApplication.run( 현재클래스명.class );
        SpringApplication.run( AppStart.class );
    }
}

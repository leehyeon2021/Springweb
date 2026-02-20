package example.종합.예제8;

// backend 프로젝트의 예제7 가져옴

// AppStart는 하위 파일이나 같은 선상의 파일만 읽을 수 있다!!

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppStart {
    public static void main(String[] args) {
        SpringApplication.run(AppStart.class);
    }
}

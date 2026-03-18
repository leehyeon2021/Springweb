package example.day012.스프링스레드;

// Config: 설정 configuration

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration // IOC 빈 어노테이션 종류 중 하나. 빈 등록.
    // 주로 미리 만들어진 설정값을 오버라이딩/등록하는 코드
public class ThreadPoolConfig {

    // ================ 서버 안정화 위함 (실무 필수) =================

    @Bean //-> 해당 함수를 등록.
        // 클래스 자체를 등록하는 것과 다르게 함수/필드 단위로 별도의 빈을 등록한다.

    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);    // 기본(최소) 실행 스레드 개수 설정
        executor.setMaxPoolSize(5);     // 최대 실행 스레드 개수 설정
            // 만일 5개 이상의 요청 및 스레드 사용 시 이후 요청은 대기 상태.
        executor.setQueueCapacity( 10 );
            // queue: 요청의 대기열(웨이팅줄)
        executor.initialize(); // 스레드풀 초기화
        return executor;
    }

    // test2를 10개 이상 요청하는 순간 예외 발생

}

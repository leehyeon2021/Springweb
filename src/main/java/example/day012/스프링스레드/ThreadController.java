package example.day012.스프링스레드;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequiredArgsConstructor
@RequestMapping("/api/thread")
public class ThreadController {
    private final ThreadService threadService;

    /*
    WAS 스레드풀 사용하여 여러 개의 스레드를 관리한다. <웹은 멀티스레드>
    매핑/요청/servlet 1개 당 스레드 1개 할당. <스레드풀>
     */
    // 1. (동기화) 요청이 들어오면 서비스에게 요청/응답
        // 1초 간격으로 1부터 10까지 누적합계 값을 반환한다.
        // * 요청이 들어오고 서비스가 응답하기 까지 컨트롤러는 기다림
    @GetMapping("/test1")
    public ResponseEntity<?> test1(){
        System.out.println("ThreadController.test1");
        return ResponseEntity.ok(threadService.test1());
    } // 실행하면 10초 뒤에 응답 옴 (10초간 Sending request, 10초 후 '55')
    // 회원가입, 로그인

    // 2. (비동기화) 요청이 들어오고 서비스가 응답하기 전에 컨트롤러는 먼저 응답
        // 냅다 응답하고 내부적으로는 코드 돌고 있음(서비스)
    @GetMapping("/test2")
    public ResponseEntity<?> test2(){
        System.out.println("ThreadController.test2");
        threadService.test2(); // 서비스 호출
        return ResponseEntity.ok(true); // 일단 아무거나 넣음 - 의미 없음
    } // @Async 없이 실행하면 10초 뒤에 응답 옴 (10초간 Sending request, 10초 후 'true')
    // @Async: 비동기는 바로 응답할 필요가 없을 때. 다운로드 가은 경우. 사용자들이 관심 없는 것들. 사용자 로그 찍을 때 void 처리해서 비동기 사용.
            // API 불러올 때 비동기 해서 내부적으로 처리하도록 데이터 처리하기.
}

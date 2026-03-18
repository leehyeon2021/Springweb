package example.day012.스프링스레드;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
@Transactional
public class ThreadService {

    public int test1(){
        int result = 0;
        for(int i=1;i<=10;i++){
            System.out.println("ThreadService.test1");
            // **스레드 일시정지**
            try{
                Thread.sleep(1000); // 1초간 일시정지
            }catch (Exception e){System.out.println(e);}
            result +=1;
        }
        return result;
    }

    // 2.
    @Async // 비동기: 먼저 반환/응답하고 내부적으로 처리
    // +) AppStart 클래스 위에 @EnableAsync 활성화 필수.
    public void test2(){
        int result = 0;
        for( int i=0;i<=10;i++){
            System.out.println("ThreadService.test2");
            try {
                Thread.sleep(1000);
            }catch (Exception e){System.out.println(e);}
            result+=1;
        }
        System.out.println("result: "+result);
    }

}

package example.day012.스프링스케줄링;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

    // * 컨트롤러 유무와 상관없이 특정 시간이 되면 서비스 자동 실행
        // 비동기 기반의 구조.
        // 목적: 보안🔒 , 자동화 , 백그라운드 처리
        // AppStart 클래스 위에 @EnableScheduling 주입한다.

    // 1. 3초 마다 실행되는 스케줄 설정
    @Scheduled( fixedRate = 3000 ) // 밀리초(3초)
    public void task1(){System.out.println("ScheduleService.task1: "+java.time.LocalTime.now());} //soutm

    // 2. 5초(변수값) 마다 실행되는 스케줄 설정
        // 변수로 스케줄링 값 입력 가능. 알림 기능 사용
    final int time = 5000; // 밀리초(5초)
    @Scheduled( fixedRate = time )
    //@Scheduled( fixedRate = 1000*60 ) // 1초*60=1분. 1000*60*60=1시간? 이렇게 계산식 넣어도 됨
    public void task2(){System.out.println("ScheduleService.task2: "+java.time.LocalTime.now());}

    // 3. 시스템의 날짜/시간 기준으로 스케줄링 (~마다X, ~때O)
        // `@Scheduled( cron = 초 분 시 일 월 요일 )`
        // *(와일드카드) = 임의의 값 들어감.
        // 특정 시간이 되면 사용자에게 알림 보내주는 기능 가능.
    @Scheduled( cron = "5 * * * * * " ) // 매 시각 5초이면 실행
    public void task3(){System.out.println("ScheduleService.task3: "+java.time.LocalTime.now());}
    @Scheduled( cron = "0 */1 * * * *") // 매 시각 1분이면 실행. 11:13:00. 컴퓨터 시간으로.
    public void task4(){System.out.println("ScheduleService.task4: "+java.time.LocalTime.now());}

/*
### cron 패턴
   1. 형식
   : @Scheduled( cron = " 초 분 시 일 월 요일 " )

   2. 첫 번째 '초': 0 ~ 59
      두 번째 '분': 0 ~ 59
      세 번째 '시': 0 ~ 23
      네 번째 '일': 1 ~ 31
      다섯 번째 '월': 1 ~ 12
      여섯 번째 '요일': 0 ~ 6 , 0일요일 ~ 3수요일 ~ 6토요일

    3. 간단 예시
       - 주말(토/일) 마다 오전 10시: @Scheduled( cron = " 0 0 10 * * 0,6 " )
          - *(매일) *(매월)0,6(쉼표 넣으면 여러 개 넣을 수 있음)
       - 일요일 마다 오전 9시: @Scheduled( cron = " 0 0 9 * * 0 " )
          - 0초 0분 9시 매일 매월 일요일
       - 매월 1일 오전 8시 30분: @Scheduled( cron = " 0 30 8 1 * * " )

    4. 비동기 == 스케줄링 == 백그라운드
       1. HTTP와 상관없이 자바(서버) 내 내부 로직 실행
       2. HTTP Response(응답) 제약 있다. 응답이 필요할 경우 추가기술 필요
          - HTTP( 무상태/비연결 ) vs. Socket( 상태/연결유지 ) vs. Message
            - HTTP: CRUD. 기초 통신. 사용자의 요청이 있어야만 응답하는 구조.
            - Socket: 실시간 양방향 통신. 사용자의 요청이 없어도 응답 받아야할 경우.
                - 예) 채팅 / 실시간데이터(배민 라이더 GPS) / 푸시알림(키오스크 알림)

*/


}

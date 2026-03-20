package example.day013.공공데이터;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service @Transactional @RequiredArgsConstructor
public class ApiService {

/*
API: 데이터 주고받고 기능을 공유할 수 있는 규칙/프로토콜(HTTP)
REST API: HTTP 기반의 API

종류
   1. 개발: SPRING CONTROLLER
   2. 활용:
      1. 공공데이터포털 API
      2. LLM(AI모델) API
      3. 사기업
        - 카카오(지도, 로그인 등)
        - 네이버(로그인, 데이터랩 등)
        - 구글(로그인, 자동입력방지/캡챠)
        - 번역( DeepL, 파파고 등)
        - 결제( 사업자등록필요. 테스트용: 카카오페이, 아임포트)
        등등

반환타입: JSON(자바에서 열기 좋음) / XML(타입변환 필요)
스프링에서 외부 HTTP를 요청 , 프로젝트/서비스1 <-통신-> 프로젝트/서비스2
    - start.spring.io에서 'Spring Reactive Web'
      - `implementation 'org.springframework.boot:spring-boot-starter-webflux'`
      - controller: 서버입장의 통신 받는 곳
      - webflux: 서버 입장에서 먼저 통신 요청
XML이란?
: <화살괄호> 사용한 마크업 언어
   - 스프링(자바)에서는 <화살괄호>안 쓰기 때문에 타입변환이 필요하다.
*/

    // * 공공데이터포털 기준: 로그인 -> 마이페이지 '개인 API인증키'
    String serviceKey = "b9c001aab838aaf168743c5eea9b492c8651a999fc31922d4edbd67c95035ea5";
    // * web 요청 객체 만들기: WebClient를 이용한 외부 HTTP 요청/응답
    private final WebClient webClient = WebClient.builder().build();

    // 1. XML 예시: 국립중앙의료원_전국 약국 정보 조회 서비스
    // https://apis.data.go.kr/B552657/ErmctInsttInfoInqireService/getParmacyFullDown?serviceKey=b9c001aab838aaf168743c5eea9b492c8651a999fc31922d4edbd67c95035ea5&pageNo=1&numOfRows=10

    // 2. JSON 예시: 인천광역시 부평구_맛있는 집(맛집) 현황
    // https://api.odcloud.kr/api/15103411/v1/uddi:b7c1c017-1d8d-4b19-8bec-c91a13927ea2?page=1&perPage=10&serviceKey=b9c001aab838aaf168743c5eea9b492c8651a999fc31922d4edbd67c95035ea5
    public Map<String,Object> test1(){ // JSON이라서 Map
        String uri = "https://api.odcloud.kr/api/15103411/v1/uddi:b7c1c017-1d8d-4b19-8bec-c91a13927ea2";
        uri+="?serviceKey="+serviceKey;
        uri+="&pageNo=1";   // 요청 매개변수1 , 페이지번호
        uri+="%perPage=67"; // 요청 매개변수2 , 페이지당 보여줄 자료 개수
        return webClient.get()
                .uri( uri )           // 1) 요청할 API 주소를 넣어준다. url vs. uri(매개변수 포함?id=1). uri가 조금 더 상위 개념이다.
                .retrieve()                 // 2) 반환/통신/응답 결과를 수신하는 함수 (반환타입)
                .bodyToMono( Map.class )    // 3) 반환 값을 자바 타입으로 변환. 즉: 반환타입이 JSON이면 Map으로 받는다.
                .block();                   // 4) 동기(처리가 끝날 때까지 대기 상태) 방식으로 결과 반환. 동기처리시필수?
    }

}
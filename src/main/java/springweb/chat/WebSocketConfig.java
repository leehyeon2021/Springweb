package springweb.chat;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

// 1️⃣ WebSocket 설치 (start.spring.io)

@Configuration                  // 빈(객체) (스프링컨테이너 예)등록, 스프링이 인식할 수 있도록, IOC
@EnableWebSocketMessageBroker   // 2️⃣ webSocket + stomp 메시지 브로커 활성화
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    // implements: 인터페이스 구현체(재정의) (vs. extents 상속(물려받기) 클래스)
    // 스프링프레임워크 장점: 인터페이스 구조라서 만들어진 기능들을 재정의(커스텀) 가능하다.
    // 오버라이딩(재정의) vs. 오버로딩(매개변수에따라 메소드/생성자 정의)
    // super(상위 객체) vs. this(현재 객체)

    // 3️⃣ 오버라이딩 (우클릭->생성->메서드구현->오버라이딩할 수 있는 목록 나옴)
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) { // registry: 기재, 등록, 등기?

        // 4️⃣ 구독(sub) 주소 설정: 클라이언트가 설정한 주소를 요청(구독/연결된상태)하면 (서버에게) 메시지를 받을 수 있다.
            // http://localhost:8080/topic/message
        registry.enableSimpleBroker( "/topic" );

        // 5️⃣ 발행 주소 설정: 클라이언트가 서버에게 메시지를 보낼 때 사용되는 주소 앞에 붙는 키워드
            // http://localhost:8080/app/~~
        registry.setApplicationDestinationPrefixes( "/app" );
    }

    // 7⃣6️⃣ websocket 접속 주소 설정: 엔드포인트( 메시지의 종착점 EndPoint )
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {

        // ️7️⃣
        registry.addEndpoint( "/ws" ) // ws: WebSocket 약자 (vs. HTTP) ws://localhost:8080
                //.setAllowedOrigins("http://localhost:5173") // 특정도메인 허용
                .setAllowedOriginPatterns("*"); // 요청 가능한 도메인들. 모든 도메인 허용(*) CORS

    }

}
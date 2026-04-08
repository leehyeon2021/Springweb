package springweb.chat;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller // 오타 조심!!
    // +) @RestController: HTTP 응답
public class ChatController {

    // DTO: 속성들을 미리 구성한 구조.
    // MAP: 속성들 직접 구조
    // @GettMapping: HTTP 요청
    @MessageMapping("/chat") // : 클라이언트가 서버에게 메시지를 보낸 주소를 매핑. ( 클라이언트 --> 서버 )
    // @ResponseBody: HTTP 응답
    @SendTo("/topic/message") // : 서버가 연동(구독)된 클라이언트들에게 메시지 응답. ( 클라이언트 <-- 서버 )
    public Map<String, Object> sendMessage( Map<String, Object> message ){
        System.out.println("ChatController.sendMessage");
        System.out.println("message = " + message);
        return message;
    }
}

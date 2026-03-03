package example.day004.ch3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // 컨트롤러(프레젠테이션) 계층
public class TestController {

    @Autowired // DI(의존성 주입)
    TestService testService;

    @GetMapping("/test")
    public List<Member> getAllMembers(){
        List<Member> members = testService.getAllMembers();
        return members;
    }

    // 앞으로 이런 거 한대 (원래 오류 남)
    @GetMapping("/test2")
    public boolean saveMember(){
        boolean result = testService.saveMember();
        return result;
    }

}

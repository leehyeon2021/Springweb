package example.day004.ch3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// 서비스 만들기!
@Service // 서비스(비즈니스) 계층
public class TestService {

    @Autowired // 빈 주입
    MemberRepository memberRepository; // MemberRepository는 인터페이스로 만듦. p.86 단계.4

    public List<Member> getAllMembers(){
        return memberRepository.findAll();
    }

}

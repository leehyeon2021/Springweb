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
        return memberRepository.findAll(); // JPA가 findAll()로 'SELECT*FROM member'를 자동으로 해주나봄... 무 서 워 요!!
        // .findAll() 은 SELECT
    }


    // 앞으로 이런 거 한대 (원래 오류 남)
    public boolean saveMember(){
        Member member = new Member( 4L, "홍길동" ); // Long이라서 L 붙임 (그냥 넘어가셈)
        memberRepository.save( member );
        // .save() 는 INSERT
        return true;
    }

}

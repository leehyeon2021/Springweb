package springweb.member.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springweb.member.repository.MemberRepository;

@Service @RequiredArgsConstructor @Transactional
public class MemberService {
    private MemberRepository memberRepository;


}

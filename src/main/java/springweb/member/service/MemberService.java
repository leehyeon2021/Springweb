package springweb.member.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import springweb.member.dto.MemberDto;
import springweb.member.entity.MemberEntity;
import springweb.member.repository.MemberRepository;

import java.util.Optional;

@Service @RequiredArgsConstructor @Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    // ** 비크립트(암호화) 객체 생성 **
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // 1. 회원가입
    public boolean signup( MemberDto memberDto ){
        // 1. 저장할 dto -> entity 변환
        MemberEntity saveEntity = memberDto.toEntity();
        // ** 최종 저장 전: 입력받은 비밀번호를 암호화 **
            // 입력받은 패스워드 암호화
        String pwd = passwordEncoder.encode( saveEntity.getMid() );
            // 암호화된 패스워드 대입
        saveEntity.setMpwd( pwd );
        // 2. jpa의 save 베소드
        MemberEntity savedEntity = memberRepository.save(saveEntity);
        // 3. 확인
        if(savedEntity.getMno() > 0){return true;} return false;
    }

    // 2. 로그인 - 암호 복호화는 안 됨(==로 비교가 안 됨). 그러나 비교는 가능(`.matches()`)
    public boolean login( MemberDto loginDto ){
        // 1. JPA(Repository) 이용, 아이디로 엔티티 찾기. SQL로 아이디/비밀번호 이
        Optional<MemberEntity> optionalMember = memberRepository.findByMid( loginDto.getMid() );
        // 2. 만약에 조회한 엔티티가 존재하면
        if( optionalMember.isPresent() ){
            // 엔티티 꺼내기
            MemberEntity memberEntity = optionalMember.get();
            // 비크립트 암호화로 평문과 암호화문 비교
                // ⭐ `==`이거 말고 `.matches()` 사용하여 비교!
                // `passwordEncoder.matches( 평문 , 암호화 );`
            boolean result = passwordEncoder.matches( loginDto.getMpwd() , memberEntity.getMpwd() );
            if(result){return true;} // 로그인 성공
            else{return false;} // 로그인 실패(패스워드와 다를 때)
        }
        return false; // 로그인 실패(아이디 없을 때)
    }

    // 4. 마이페이지
    public MemberDto myInfo( String loginMid ){
        // 1. 로그인된 mid를 받아서 리포지토리에서 찾는다.
        Optional<MemberEntity> entityOptional = memberRepository.findByMid( loginMid );
        // 2. 만약에 엔티티가 존재하면 엔티티 꺼내서 dto로 변환해서 반환한다.
        if(entityOptional.isPresent()){return entityOptional.get().toDto();}
        return null; // 없으면 null 반환.
    }
}

/*
비밀번호 암호화 하기!

## 암호화
1. 정의
: 자료를 보호하기 위해 사람이 이해하기 어려운 데이터로 변환
2. 목적
: 자료 보호, 신뢰성, 무결성 유지
3. 사용처
: 비밀번호, 금융, HTTPS(Security) 등등. 보호가 필요한 곳.
4. 용어
   1. 평문: 원래의 자료
   2. 암호문: 암호화된 자료
   3. 암호화: 평문 자료를 암호문으로 변환하는 과정
      1. 단방향 암호화: 평문을 암호문으로 변환. 평문으로 변환 불가능한 상태. < 복호화 없음 >
      2. 양방향 암호화: 평문을 암호문으로 변환하고 다시 평문으로 변환 가능. < 암호화+복호화 >
   4. 복호화: 암호문 자료를 평문으로 변환하는 과정
   5. 해시 함수: 자료를 고정된 길이로 변환하는 함수
      - 자바: Object 클래스 `.hashCode()`. 객체 주소값을 해시코드로 반환.
      - 서로 다른 자료들을 **동일한** 길이로 변환하는 함수
      - 임의의 계산식으로 변환하는 과정이기에 다시 되돌리기는 불가능하다.
         - '사과' --> A1B2C3(그냥예시임)
         - '사과' --> A1B2C3  => 같은 자료는 같은 해시값, 다른 자료는 다른 해시값으로
         - '바나나' -> X1C2V3  => 단, 서로 다른 자료도 일정한 길이로 변환한다.
   6. 솔트 함수: 암호화할 때 사용되는 랜덤값.
      - 해시 함수에 소금 쳐서 같은 자료도 서로 다른 해시 함수로 나오게 함ㅋㅋ.
      - 동일한 계산식(알고리즘/해시)의 서로 다른 결과값
         - '사과' ->솔트 추가-> A1B5T1
5. 종류
   1. 비밀번호 : Bcrypt(비크립트) , 해시 함수 , 단방향 , 복호화 없음 , SHA
   2. 전자서명/파일 : SHA-256 , 해시 함수 , 단방향 , 복호화 없음 , SHA-비트(예-sha-512).
      - bit: 0또는1 , 101==3bit.
   3. 웹통신 : HTTPS( TLS ) , HTTP( 암호화 안 된 상태(보안없음) )
6. 비크립트
   1. 정의
   : 해시 함수를 이용하여 주로 비밀번호 암호화할 때 사용한다.
   2. 특징
      - 솔트(salt): 같은 비밀번호라도 랜덤(salt)값으로 서로 다른 암호화된 결과를 만든다.
      - 반복연산적용: 계산식을 여러 번 하여 검증 속도 늦춤
      - 원본 복구 불가능: 단방향 암호화문이라 비밀번호 찾기는 못 함. 대신 임시비밀번호 부여/수정 해야 함.
   3. 형태 (60자이상)
      - $2a$10$4SK14UKYY9J2SWeZkrzcY.gm1yGq.zbzSsoCKc3vR/g4YeQ5J.W7u
         - $2a : 비크립트 버전
         - $10 : 반복연산수, 제곱근
         - $4SK14UKYY9J2SWeZkrzcY : salt(22자)
         - .gm1yGq.zbzSsoCKc3vR/g4YeQ5J.W7u : 해시값
      - 평문과 암호문을 비교할 떄는 암호문의 연산수와 salt와 해시값으로 평문을 암호화해서 비교한다.
   4. 설치
      1: SPRING 시큐리티 포함 : `implementation 'org.springframework.boot:spring-boot-starter-security'`
      2. SPRING 시큐리티 내 비크립트만: `implementation 'org.springframework.security:spring-security-crypto:6.4.4' <- 이거 함
   5. 사용법
      1. 회원가입(비밀번호 암호화)
         - `BCryptPasswordEncoder 암호객체 = new BCryptPasswordEncoder();`
         - `String 암호화된 값 = 암호객체.encode( 암호화할 자료 );`
      2. 로그인(비밀번호 일치 확인)
         - `boolean 비교결과 = 암호객체.matches( 평문 , 암호문 );`


로그인은 `.setAttribute()` , 로그아웃은 `.removeAttribute()` , 꺼내올 때는 `.getAttribute()`
*/
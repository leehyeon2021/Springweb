package springweb.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import springweb.member.entity.MemberEntity;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity,Long>{

    // 1. 아이디로 엔티티 찾기
    // `findBy필드명( 값 )`: 필드명은 카멜 규칙!!
    //MemberEntity findByMid( String mid ); 이렇게 바로 Entity로 받을 수 있기는 한데 Optional로 받는 게 정석이다.
    Optional<MemberEntity>findByMid(String mid);
    // vs. (저번에 배운 거)
    //@Query( value = "select * from member where mid = :mid" , nativeQuery = true )
    //MemberEntity query( String mid );
}

package springweb.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springweb.member.entity.MemberEntity;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity,Long>{}

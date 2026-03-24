package springweb.board.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springweb.board.dto.BoardDto;
import springweb.board.entity.BoardEntity;
import springweb.board.repository.BoardRepository;
import springweb.member.entity.MemberEntity;
import springweb.member.repository.MemberRepository;

import java.util.Optional;

@Service@Transactional @RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository; //서비스 간 호출/참조 가능.

    // 1. 글쓰기 (회원)
    public boolean write(BoardDto boardDto, String loginMid ){
        // 1. Dto -> Entity
        BoardEntity saveEntity = boardDto.toEntity();
        // **** 저장하기 전에 FK 대입하기. FK의 엔티티를 찾아서 대입 ****
        // 현재 로그인 중인 mid로 엔티티 찾기
        Optional<MemberEntity> entityOptional = memberRepository.findByMid( loginMid );
        if(!entityOptional.isPresent()){return false;} // 존재하지 않으면 존재하지 않은 회원이므로 실패.
        // ⭐ 저장할 게시물 엔티티에 `.set참조엔티티( 회원엔티티 );` 번호 넣는 거 아님.
        saveEntity.setMemberEntity( entityOptional.get() );
        // *******************************************************
        // 2. entity 저장
        BoardEntity savedEntity = boardRepository.save(saveEntity);
        // 3. 만약
        if(savedEntity.getBno()>0){return true;}else{return false;}
    }

}

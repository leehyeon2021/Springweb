package example.종합.예제10.board.service;

import example.종합.예제10.board.dto.BoardDto;
import example.종합.예제10.board.entity.BoardEntity;
import example.종합.예제10.board.repository.BoardRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BoardService {
    @Autowired private BoardRepository boardRepository;

    // 1. 등록 (Service는 HTTP를 담당하지 않는다.)
    public boolean 등록(BoardDto boardDto){
        // 1. 저장할 dto -> entity 변환한다.
        BoardEntity saveEntity = boardDto.toEntity();
        // 2. JPA 이용한 entity 저장
        saveEntity = boardRepository.save(saveEntity);
        // 3. pk생성여부 확인
        if(saveEntity.getBno()>=1)return true;
        return false;
    }

    // 2. 전체조회
    public List<BoardDto> 전체조회(){
        // 1. 조회
        List<BoardEntity> boardEntity = boardRepository.findAll();
        // 2. 조회된 모든 엔티티 -> dto 변환 , 담기
        List<BoardDto> boardDtos = new ArrayList<>();
        boardEntity.forEach(entity -> {
            BoardDto list = entity.toDto();
            boardDtos.add(list);
        });
        return boardDtos;
    }

    // 3. 개별조회
    public BoardDto 개별조회(int bno){
        // 1. 조회 할 번호 조회
        Optional<BoardEntity> optional = boardRepository.findById(bno);
        // 2. 만약 존재하면 entity->dto 변환
        if(optional.isPresent()){
            BoardEntity entity = optional.get();
            BoardDto boardDto = entity.toDto();
            return boardDto;
        }
        return null;
    }

    // 4. 개별수정
    public boolean 개별수정(BoardDto boardDto){
        // 1. 수정할 게시물번호로 엔티티 찾기
        Optional<BoardEntity> optional = boardRepository.findById(boardDto.getBno());
        // 2. 만약에 엔티티가 존재하면
        if(optional.isPresent()){
            // 3. 엔티티 내 멤버변수들을 수정한다. (영속성 적용됨)
            BoardEntity updateEntity = optional.get();
            updateEntity.setBcontent(boardDto.getBcontent());
            updateEntity.setBtitle(boardDto.getBtitle());
            updateEntity.setBwriter(boardDto.getBwriter());
            // 4. 반환한다.
            return true;
        }
        return false;
    }

    // 5. 개별삭제
    public boolean 개별삭제(int bno){
        // 1. 삭제할 게시물번호가 존재하는지 확인
        if(boardRepository.existsById(bno)){ // .existsById( pk번호 ) : 존재하면 true , 없으면 false.
            // 2. 만약에 존재하면 삭제.
            boardRepository.deleteById(bno);
            return true;
        }
        return false;
    }

}

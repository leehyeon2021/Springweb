package example.종합.예제10.board.service;

import example.종합.예제10.board.dto.BoardDto;
import example.종합.예제10.board.entity.BoardEntity;
import example.종합.예제10.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
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

}

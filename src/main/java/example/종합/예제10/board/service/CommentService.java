package example.종합.예제10.board.service;

import example.종합.예제10.board.dto.CommentDto;
import example.종합.예제10.board.entity.CommentEntity;
import example.종합.예제10.board.repository.CommentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service @Transactional
public class CommentService {
    @Autowired private CommentRepository commentRepository;

    // 댓글쓰기
    public boolean 쓰기(CommentDto commentDto){
        // 변환
        CommentEntity saveEntity = commentDto.toEntity();
        // 저장
        saveEntity = commentRepository.save(saveEntity);
        // 확인
        if(saveEntity.getCno()>=1)return true; return false;
    }

    // 댓글전체조회 - bno를 받아와서(@RequestParam) 여기서 같은지 확인
    public List<CommentDto> 조회(int bno){
        List<CommentEntity> entitys = commentRepository.findAll();
        List<CommentDto> list = new ArrayList<>();
        entitys.forEach(entity -> {
            // bno(페이지)를 fk로 사용. 댓글bno와 페이지bno가 같은지 확인 후 응답!!
            if(entity.getBno()==bno){
                CommentDto commentDto = entity.toDto();
                list.add(commentDto);
            }
        });
        return list;
    }

    // 댓글삭제
    public boolean 삭제(int cno){
        // 번호 맞니 맞으면 지워
        if(commentRepository.existsById(cno)){
            commentRepository.deleteById(cno);
            return true;
        } return false;
    }

    // 댓글수정
    public boolean 수정(CommentDto commentDto){
        // 번호로 엔티티 찾아
        Optional<CommentEntity> optional = commentRepository.findById(commentDto.getCno());
        // 존재하면 수정해
        if(optional.isPresent()){
            CommentEntity list = optional.get();
            list.setCno(commentDto.getCno());
            list.setCcontent(commentDto.getCcontent());
            list.setCwriter(commentDto.getCwriter());
            list.setBno(commentDto.getBno());
            return true;
        }return false;
    }

}

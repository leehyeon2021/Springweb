package example.종합.예제9.controller;

import example.종합.예제9.model.dao.BoardDao;
import example.종합.예제9.model.dto.BoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BoardController {

    @Autowired // 의존성 주입: 등록된 빈(객체) 가져오기
    private BoardDao boardDao;

    // 1. 전체 조회
    @GetMapping("/board") // localhost:8080/board
    public List<BoardDto> findAll(){
        List<BoardDto> result = boardDao.findAll();
        return result;
    }

    // 2. 게시물 등록
    @PostMapping("/board")
    public boolean write(@RequestBody BoardDto boardDto){
        boolean result = boardDao.write( boardDto );
        return result;
    }

    // 3. 게시물 수정
    @PutMapping("/board")
    public boolean update( @RequestBody BoardDto boardDto){
        boolean result = boardDao.update( boardDto );
        return result;
    }

    // 4. 게시물 삭제
    @DeleteMapping("/board")
    public boolean delete( @RequestParam int bno ){
        boolean result = boardDao.delete( bno );
        return result;
    }


}

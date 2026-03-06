package example.종합.예제10.board.controller;

import example.종합.예제10.board.dto.BoardDto;
import example.종합.예제10.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/board")
public class BoardController {
    @Autowired private BoardService boardService;

    // 1. 등록
    @PostMapping
    public boolean 등록(@RequestBody BoardDto boardDto){
        return boardService.등록(boardDto);
    }

    // 2. 전체조회
    @GetMapping
    public List<BoardDto> 전체조회(){
        return boardService.전체조회();
    }

    // 3. 개별조회
    @GetMapping("/detail")
    public BoardDto 개별조회(@RequestParam int bno){
        return boardService.개별조회(bno);
    }
}

package example.종합.예제10.board.controller;

import example.종합.예제10.board.dto.CommentDto;
import example.종합.예제10.board.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired private CommentService commentService;

    // 댓글쓰기
    @PostMapping
    public boolean 쓰기(@RequestBody CommentDto commentDto){
        return commentService.쓰기(commentDto);
    }

    // 댓글전체조회
    @GetMapping
    public List<CommentDto> 조회(){
        return commentService.조회();
    }

    // 댓글삭제
    @DeleteMapping
    public boolean 삭제(@RequestParam int cno){
        return commentService.삭제(cno);
    }


    // 댓글수정
    @PutMapping
    public boolean 수정(@RequestBody CommentDto commentDto){
        return commentService.수정(commentDto);
    }

}

package example.day002.practice2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/board")

public class BoardController {

    List< BoardDto > list = new ArrayList<>();

    // 1. 게시물등록 POST
    @PostMapping
    public boolean boardWrite( @RequestBody BoardDto boardDto ){
        if(list.add(boardDto)){
            System.out.println("와");
            return true;
        }
        System.out.println("안 됨");
        return false;
    }

    // 2. 게시물 전체조회 GET
    @GetMapping
    public List<BoardDto> boardPrint(){
        return list;
    }

    // 3. 게시물 개별조회
    @GetMapping("/detail")
    public BoardDto boardDetail( @RequestParam int bno ){
        return list.get(bno-1);
    }

    // 4. 게시물 삭제
    @DeleteMapping
    public boolean boardDelete( @RequestParam int bno ){
        // removeIf: ArrayList에서. 조건 충족하는 list 요소 삭제 / 반환: true/false
        if(list.removeIf(board -> board.getBno() == bno) ){
            return true;
        }
            return false;
    }

    // 5. 게시물 수정
    @PutMapping
    public boolean boardUpdate( @RequestBody BoardDto boardDto ){ // @RequestParam vs. @RequestBody -> DTO라서 body로 받아야 함.
        for(int i = 0; i<list.size();i++){
                if(list.get(i).getBno() == boardDto.getBno()){
                    list.set(i, boardDto);
                    return true;
                }
            }
        return false;
    }


}
package example.day005.practice5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/day005/book")
public class BookController {
    @Autowired
    private BookService bookService;

    // 도서 등록
    @PostMapping
    public boolean bAdd( @RequestBody BookDto bookDto ){
        boolean result = bookService.bAdd( bookDto );
        return result;
    }

    // 도서 전체 조회
    @GetMapping
    public List<BookDto> bFindAll(){
        List<BookDto> result = bookService.bFindAll();
        return result;
    }

    // 특정 도서 수정
    @PutMapping
    public boolean bUpdate( @RequestBody BookDto bookDto ){
        boolean result = bookService.bUpdate( bookDto );
        return result;
    }

    // 특정 도서 삭제
    @DeleteMapping
    public boolean bDelete(@RequestParam int bid){
        boolean result = bookService.bDelete( bid );
        return result;
    }

}

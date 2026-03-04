package example.day005.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // 여긴 컨트롤러
@RequestMapping("/day005/exam") // localhost:8080/day005/exam
public class ExamController {
    @Autowired // 빈 주입 - Controller는 Service 불러옴.
    private ExamService examService;

    // R 조회 select ( localhost:8080/day005/exam )
    @GetMapping
    public List< ExamDto > 전체조회(){
        List<ExamDto> result = examService.전체조회();
        return result;
    }

    // C 쓰기 insert {"ename": "내이름"}
    @PostMapping
    public boolean 저장(@RequestBody ExamDto examDto){
        boolean result = examService.저장( examDto );
        return result;
    }

    // D 삭제 delete
    @DeleteMapping
    public boolean 삭제( @RequestParam int eno ){
        boolean result = examService.삭제( eno );
        return result;
    }

    // U 수정 update
    @PutMapping
    public boolean 수정( @RequestBody ExamDto examDto ){
        boolean result = examService.수정( examDto );
        return result;
    }
}

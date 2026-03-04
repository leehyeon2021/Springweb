package example.day005.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController // 여긴 컨트롤러
public class ExamController {
    @Autowired // 빈 주입 - Controller는 Service 불러옴.
    private ExamService examService;
}

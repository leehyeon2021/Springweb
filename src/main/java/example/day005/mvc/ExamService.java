package example.day005.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // 여긴 서비스
public class ExamService {
    @Autowired // 빈 주입 - Service는 Repository 불러옴.
    private ExamRepository examRepository;
}

package example.day007.practice7.service;

import example.day007.practice7.repository.CourseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service @Transactional
public class CourseService {
    @Autowired private CourseRepository cr;

    // 등록기능1

}

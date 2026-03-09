package example.day007.practice7.service;

import example.day007.practice7.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class StudentService {
    @Autowired private StudentRepository sr;

    // 등록기능2

}

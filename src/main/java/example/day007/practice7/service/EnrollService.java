package example.day007.practice7.service;

import example.day007.practice7.repository.EnrollRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class EnrollService {
    @Autowired private EnrollRepository er;

    // 등록기능3
}

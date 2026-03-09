package example.day007.practice7.service;

import example.day007.practice7.dto.CourseDto;
import example.day007.practice7.entity.CourseEntity;
import example.day007.practice7.repository.CourseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service @Transactional
public class CourseService {
    @Autowired private CourseRepository cr;
    // 등록기능1
    public boolean cSave(CourseDto courseDto){
        // dto -> entity
        // JPA save로 엔티티 insert
        // 성공 판단
        return cr.save(courseDto.toEntity()).getCourseId()>=1;
    }
}

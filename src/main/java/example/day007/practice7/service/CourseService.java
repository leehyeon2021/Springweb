package example.day007.practice7.service;

import example.day007.practice7.dto.CourseDto;
import example.day007.practice7.entity.CourseEntity;
import example.day007.practice7.repository.CourseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service @Transactional
public class CourseService {
    @Autowired private CourseRepository cr;
    // 등록기능1
    public boolean cSave(CourseDto courseDto){
        // dto -> entity
        // JPA save로 엔티티 insert
        // 성공 판단
        return cr.save(courseDto.toEntity()).getCourseid()>=1;
    }

    // 교수님 - DTO 없이 Map 사용하심
    public boolean addC(Map<String,Object> map){
        // 1. map/dto -> entity 변환한다.
        CourseEntity saveEntity = new CourseEntity();
        saveEntity.setCourseName(String.valueOf(map.get("courseName")));
        // 2. 변환할 entity 저장한다.
        CourseEntity saved = cr.save(saveEntity);
        // 3. 반환된 pk 확인
        if(saved.getCourseid()>=1)return true;return false;
    }
}

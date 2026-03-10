package example.day007.practice7.service;

import example.day007.practice7.dto.StudentDto;
import example.day007.practice7.entity.CourseEntity;
import example.day007.practice7.entity.StudentEntity;
import example.day007.practice7.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Transactional
public class StudentService {
    @Autowired private StudentRepository sr;

    // 등록기능2
    public boolean sSave(StudentDto studentDto){
     return sr.save(studentDto.toEntity()).getStudentid()>=1;
    }

    // 교수님 - DTO 없이 Map 사용하심
    public boolean addS(Map<String,Object> map){
        // 1. map/dto -> entity 변환한다.
        StudentEntity saveEntity = new StudentEntity();
        saveEntity.setStudentName(String.valueOf(map.get("studentName")));
        // 2. 변환할 entity 저장한다.
        StudentEntity saved = sr.save(saveEntity);
        // 3. 반환된 pk 확인
        if(saved.getStudentid()>=1)return true;return false;
    }
}

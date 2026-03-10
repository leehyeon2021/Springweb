package example.day007.practice7.service;

import example.day007.practice7.dto.EnrollDto;
import example.day007.practice7.entity.CourseEntity;
import example.day007.practice7.entity.EnrollEntity;
import example.day007.practice7.entity.StudentEntity;
import example.day007.practice7.repository.CourseRepository;
import example.day007.practice7.repository.EnrollRepository;
import example.day007.practice7.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service @Transactional
public class EnrollService {
    @Autowired private EnrollRepository er;

    // 교수님이 사용하심
    @Autowired private CourseRepository cr;
    @Autowired private StudentRepository sr;

    // 등록기능3 -> 등록기능이 잘못된 듯??? 교수님이 알려주신 addE로 하고 eFindAll 했는데 잘 나옴
    // 재미나이: "fk 저장할 땐 숫자 넣는 게 아니라 findById로 찾아온 진짜 객체를 넣어줘야 한다." ok...
    public boolean eSave(EnrollDto enrollDto){
        CourseEntity courseEntity = cr.findById(enrollDto.getCourseid()).orElseThrow();
        StudentEntity studentEntity = sr.findById(enrollDto.getStudentid()).orElseThrow();
        EnrollEntity enrollEntity = EnrollEntity.builder()
                .status(enrollDto.getStatus()).courseEntity(courseEntity).studentEntity(studentEntity)
                .build();
        return er.save(enrollEntity).getEnrollid()>=1;
    }

    // 조회기능 - 전체
    public List<EnrollDto> eFindAll(){
        List<EnrollEntity> enrollEntityList = er.findAll();
        List<EnrollDto> enrollDtoList = new ArrayList<>();
        enrollEntityList.forEach(entity ->{
            System.out.println(entity);
            EnrollDto enrollDto = entity.toDto();
            enrollDtoList.add(enrollDto);
        });
        System.out.println(enrollDtoList);
        return enrollDtoList;
    }

    // 교수님 - DTO 없이 Map 사용하심
    public boolean addE(Map<String,Object> map){
        // 1. 수강신청 엔티티 생성
        EnrollEntity saveEntity = new EnrollEntity();
        saveEntity.setStatus( String.valueOf( map.get("status")));
            // ******** FK 넣기: FK에 해당하는 엔티티 대입 *********
        // [1] fk 확인한다. dto/map 등등
        int courseid = Integer.parseInt(String.valueOf( map.get("courseid")));
        // [2] fk에 해당하는 엔티티 찾는다.
        Optional<CourseEntity> optionalCourse = cr.findById(courseid);
        if(optionalCourse.isPresent()){ // [3] 만약에 해당하는 엔티티가 존재하면
            CourseEntity courseEntity = optionalCourse.get(); // [4] 엔티티 꺼내기
            saveEntity.setCourseEntity(courseEntity); // [5] fk 엔티티를 단방향에 저장한다.
        }else{return false;}
            // ******** FK 넣기: FK에 해당하는 엔티티 대입 *********
        int studentid = Integer.parseInt(String.valueOf(map.get("studentid")));
        Optional<StudentEntity> optionalStudent = sr.findById(studentid);
        if(optionalStudent.isPresent()) {
            StudentEntity studentEntity = optionalStudent.get();
            saveEntity.setStudentEntity(studentEntity);
        }else{return false;}
        // ************************
        // 2. 수강신청 엔티티 저장
        EnrollEntity saved = er.save(saveEntity);
        // 3. 확인
        if(saved.getEnrollid()>=1)return true;return false;
    }
    // 조회 - 개별
    public Map<String, Object>get(int enrollid){
        Optional<EnrollEntity> optionalEnroll = er.findById(enrollid);
        if(optionalEnroll.isPresent()){
            EnrollEntity enrollEntity = optionalEnroll.get();
            Map<String,Object> map = new HashMap<>();
            map.put("enrollid",enrollEntity.getEnrollid());
            map.put("status",enrollEntity.getStatus());
            map.put("createDate",enrollEntity.getCreateDate());
            map.put("updateDate",enrollEntity.getUpdateDate());
            map.put("courseName",enrollEntity.getCourseEntity().getCourseName());
            map.put("studentName",enrollEntity.getStudentEntity().getStudentName());
            return map;
        }return null;
    }
}

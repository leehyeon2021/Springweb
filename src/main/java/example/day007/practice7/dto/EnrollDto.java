package example.day007.practice7.dto;

import example.day007.practice7.entity.CourseEntity;
import example.day007.practice7.entity.EnrollEntity;
import example.day007.practice7.entity.StudentEntity;
import lombok.*;

@AllArgsConstructor @NoArgsConstructor @Data @Builder
public class EnrollDto {
    private Integer enrollid;
    private String status;

    private Integer courseid;
    private Integer studentid;
    private String courseName;
    private String studentName;

    // 아래는 객체를 입력받아야 하는데 내가 자꾸 숫자를 넣음(이런!!)
    private CourseDto courseDto;
    private StudentDto studentDto;

    private String createDate;
    private String updateDate;

    public EnrollEntity toEntity(){
        return EnrollEntity.builder()
                .enrollid(this.enrollid).status(this.status)
                .build();
    }
}

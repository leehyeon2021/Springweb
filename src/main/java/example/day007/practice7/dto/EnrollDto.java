package example.day007.practice7.dto;

import example.day007.practice7.entity.CourseEntity;
import example.day007.practice7.entity.EnrollEntity;
import example.day007.practice7.entity.StudentEntity;
import lombok.*;

@AllArgsConstructor @NoArgsConstructor @Data @Builder
public class EnrollDto {
    private Integer enrollId;
    private String status;

    private CourseEntity courseId;
    private StudentEntity studentId;

    private String createDate;
    private String updateDate;

    public EnrollEntity toEntity(){
        return EnrollEntity.builder().enrollId(this.enrollId).status(this.status).courseId(this.courseId).studentId(this.studentId).build();
    }
}

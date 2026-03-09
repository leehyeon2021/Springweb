package example.day007.practice7.dto;

import example.day007.practice7.entity.StudentEntity;
import lombok.*;

@AllArgsConstructor @NoArgsConstructor @Data @Builder
public class StudentDto {
    private Integer studentId;
    private String studentName;
    private String createDate;
    private String updateDate;

    public StudentEntity toEntity(){
        return StudentEntity.builder().studentId(this.studentId).studentName(this.studentName).build();
    }
}

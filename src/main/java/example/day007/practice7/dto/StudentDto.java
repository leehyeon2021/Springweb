package example.day007.practice7.dto;

import example.day007.practice7.entity.StudentEntity;
import lombok.*;

@AllArgsConstructor @NoArgsConstructor @Data @Builder
public class StudentDto {
    private Integer studentid;
    private String studentName;
    private String createDate;
    private String updateDate;

    public StudentEntity toEntity(){
        return StudentEntity.builder().studentid(this.studentid).studentName(this.studentName).build();
    }
}

package example.day007.practice7.entity;

import example.day007.practice7.dto.StudentDto;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor @NoArgsConstructor @Data @Builder
@Entity @Table(name = "student")
public class StudentEntity extends BaseTime{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "학생번호")
    private Integer studentId;
    @Column(name = "학생명")
    private String studentName;

    public StudentDto toDto(){
        return StudentDto.builder().studentId(this.studentId).studentName(this.studentName).createDate(getCreateDate().toString()).updateDate(getUpdateDate().toString()).build();
    }
}

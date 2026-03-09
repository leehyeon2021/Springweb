package example.day007.practice7.entity;

import example.day007.practice7.dto.StudentDto;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor @NoArgsConstructor @Data @Builder
@Entity @Table(name = "student")
public class StudentEntity extends BaseTime{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "studentId")
    private Integer studentId;
    @Column(name = "studentName")
    private String studentName;

    public StudentDto toDto(){
        return StudentDto.builder().studentId(this.studentId).studentName(this.studentName).createDate(getCreateDate().toString()).updateDate(getUpdateDate().toString()).build();
    }
}

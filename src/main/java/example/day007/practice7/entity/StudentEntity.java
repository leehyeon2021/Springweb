package example.day007.practice7.entity;

import example.day007.practice7.dto.StudentDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor @Data @Builder
@Entity @Table(name = "student")
public class StudentEntity extends BaseTime{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "studentid")
    private Integer studentid;
    @Column(name = "studentName")
    private String studentName;

    // 양방향
    @OneToMany(mappedBy = "studentEntity") @ToString.Exclude @Builder.Default
    private List<EnrollEntity> enrollEntityList = new ArrayList<>();

    public StudentDto toDto(){
        return StudentDto.builder().studentid(this.studentid).studentName(this.studentName).createDate(getCreateDate().toString()).updateDate(getUpdateDate().toString()).build();
    }
}

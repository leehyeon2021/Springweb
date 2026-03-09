package example.day007.practice7.entity;

import example.day007.practice7.dto.CourseDto;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor @NoArgsConstructor @Data @Builder
@Entity @Table(name = "course")
public class CourseEntity extends BaseTime{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "과정번호")
    private Integer courseId;
    @Column(name = "과정명")
    private String courseName;

    public CourseDto toDto(){
        return CourseDto.builder().courseId(this.courseId).courseName(this.courseName).createDate(getCreateDate().toString()).updateDate(getUpdateDate().toString()).build();
    }
}

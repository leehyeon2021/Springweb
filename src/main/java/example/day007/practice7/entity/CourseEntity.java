package example.day007.practice7.entity;

import example.day007.practice7.dto.CourseDto;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor @NoArgsConstructor @Data @Builder
@Entity @Table(name = "course")
public class CourseEntity extends BaseTime{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "courseId")
    private Integer courseId;
    @Column(name = "courseName")
    private String courseName;

    public CourseDto toDto(){
        return CourseDto.builder().courseId(this.courseId).courseName(this.courseName).createDate(getCreateDate().toString()).updateDate(getUpdateDate().toString()).build();
    }
}

package example.day007.practice7.entity;

import example.day007.practice7.dto.CourseDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor @Data @Builder
@Entity @Table(name = "course")
public class CourseEntity extends BaseTime{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "courseid")
    private Integer courseid;
    @Column(name = "courseName")
    private String courseName;

    // 양방향
    @OneToMany(mappedBy = "courseEntity") @ToString.Exclude @Builder.Default
    private List<EnrollEntity> enrollEntityList = new ArrayList<>();

    public CourseDto toDto(){
        return CourseDto.builder().courseid(this.courseid).courseName(this.courseName).createDate(getCreateDate().toString()).updateDate(getUpdateDate().toString()).build();
    }
}

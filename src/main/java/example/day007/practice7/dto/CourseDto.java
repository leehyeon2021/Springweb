package example.day007.practice7.dto;

import example.day007.practice7.entity.CourseEntity;
import lombok.*;

@AllArgsConstructor @NoArgsConstructor @Data @Builder
public class CourseDto {
    private Integer courseid;
    private String courseName;
    private String createDate;
    private String updateDate;

    public CourseEntity toEntity(){
        return CourseEntity.builder().courseid(this.courseid).courseName(this.courseName).build();
    }
}

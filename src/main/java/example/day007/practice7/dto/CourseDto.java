package example.day007.practice7.dto;

import example.day007.practice7.entity.CourseEntity;
import lombok.*;

@AllArgsConstructor @NoArgsConstructor @Data @Builder
public class CourseDto {
    private Integer courseId;
    private String courseName;
    private String createDate;
    private String updateDate;

    public CourseEntity toEntity(){
        return CourseEntity.builder().courseId(this.courseId).courseName(this.courseName).build();
    }
}

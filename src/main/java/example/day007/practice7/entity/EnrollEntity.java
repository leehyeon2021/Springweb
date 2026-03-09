package example.day007.practice7.entity;

import example.day007.practice7.dto.EnrollDto;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor @NoArgsConstructor @Data @Builder
@Entity @Table(name = "enroll")
public class EnrollEntity extends BaseTime{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enrollId")
    private Integer enrollId;
    @Column(name = "status")
    private String status;

    @ManyToOne( cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JoinColumn(name = "courseId")
    private CourseEntity courseId;
    @ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JoinColumn(name = "studentId")
    private StudentEntity studentId;

    public EnrollDto toDto(){
        return EnrollDto.builder().enrollId(this.enrollId).status(this.status).courseId(this.courseId).studentId(this.studentId).createDate(getCreateDate().toString()).updateDate(getUpdateDate().toString()).build();
    }
}

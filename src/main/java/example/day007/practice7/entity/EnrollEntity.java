package example.day007.practice7.entity;

import example.day007.practice7.dto.EnrollDto;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor @NoArgsConstructor @Data @Builder
@Entity @Table(name = "enroll")
public class EnrollEntity extends BaseTime{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enrollid")
    private Integer enrollid;
    @Column(name = "status")
    private String status;

    // 단방향1
    @ManyToOne( cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JoinColumn(name = "courseid")
    private CourseEntity courseEntity;
    // 단방향2
    @ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JoinColumn(name = "studentid")
    private StudentEntity studentEntity;

    public EnrollDto toDto(){
        return EnrollDto.builder()
                .enrollid(this.enrollid).status(this.status)
                .courseid(this.courseEntity.getCourseid()).studentid(this.studentEntity.getStudentid())
                .courseName(courseEntity.getCourseName()).studentName(studentEntity.getStudentName())
                .createDate(getCreateDate().toString()).updateDate(getUpdateDate().toString())
                        .build();
    }
}

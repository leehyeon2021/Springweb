package practice.practice1.entity;

import jakarta.persistence.*;
import lombok.*;
import practice.practice1.dto.TaskDto;

@Entity
@Table(name = "task")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TaskEntity extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 50)
    private String title;
    @Column
    private String content;
    private String requester;
    private String status;

    public TaskDto toDto(){
        return TaskDto.builder()
                .id(this.id).title(this.title).content(this.content).requester(this.requester).status(this.status)
                .createAt(getCreateAt().toString()).updateAt(getUpdateAt().toString())
                .build();
    }

}

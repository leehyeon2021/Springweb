package practice.practice1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import practice.practice1.entity.TaskEntity;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TaskDto {
    private Integer id;
    private String title;
    private String content;
    private String requester;
    private String status;
    private String updateAt;
    private String createAt;

    public TaskEntity toEntity(){
        return TaskEntity.builder()
                .id(this.id).title(this.title).content(this.content).requester(this.requester).status(this.status)
                .build();
    }
}

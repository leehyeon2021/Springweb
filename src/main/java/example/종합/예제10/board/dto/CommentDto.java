package example.종합.예제10.board.dto;

import example.종합.예제10.board.entity.CommentEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data @Builder
public class CommentDto {
    private Integer cno;
    private String ccontent;
    private String cwriter;
    private String createDate;
    private String updateDate;
    private Integer bno;

    // dto -> entity
    public CommentEntity toEntity(){
        return CommentEntity.builder()
                .cno(this.cno).ccontent(this.ccontent).cwriter(this.cwriter)
                .bno(this.bno)
                            .build();
    }
}

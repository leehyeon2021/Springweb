package example.종합.예제10.board.entity;

import example.종합.예제10.board.dto.CommentDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data @Builder
@Entity @Table( name = "comment")
public class CommentEntity extends BaseTime{
    @Id @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column(name = "댓글번호(pk)")
    private Integer cno;
    @Column(name = "댓글내용", columnDefinition = "longtext not null")
    private String ccontent;
    @Column(name = "댓글작성자", length = 30 , nullable = false )
    private String cwriter;
    @Column(name = "게시물번호(fk)")
    private Integer bno;

    // entity -> dto
    public CommentDto toDto(){
        return CommentDto.builder()
                .cno(this.cno).ccontent(this.ccontent).cwriter(this.cwriter)
                .createDate(getCreateDate().toString()).updateDate(getUpdateDate().toString())
                .bno(this.bno)
                         .build();
    }
}

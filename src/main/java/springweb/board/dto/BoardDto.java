package springweb.board.dto;

import lombok.*;
import springweb.board.entity.BoardEntity;

@NoArgsConstructor @AllArgsConstructor @Data @Builder
public class BoardDto {
    // =================등록용=================
    private Long bno; // 게시물 번호
    private String btitle; // 게시물 제목
    private String bcontent; // 게시물 내용
    private String bfile; // 게시물 참부파일

    // =================조회용=================
    private String createDate;
    private String updateDate;
    // (단방향)entity 정보는 DTO에 포함하지 않는다.
    private Long mno;
    private String mid;

    public BoardEntity toEntity(){ // 주로 등록용
        return BoardEntity.builder()
                .bno(this.bno).btitle(this.btitle).bcontent(this.bcontent).bfile(this.bfile)
                // memberEntity fk는 서비스에서 대입하는 걸로 하는 컨셉
                .build();
    }
}

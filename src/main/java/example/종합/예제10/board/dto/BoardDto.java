package example.종합.예제10.board.dto;

import example.종합.예제10.board.entity.BoardEntity;
import lombok.*;

@NoArgsConstructor @AllArgsConstructor @Data @Builder
public class BoardDto {
    private Integer bno;
    private String btitle;
    private String bcontent;
    private String bwriter;
    private String createDate;
    private String updateDate;

    // dto --> entity ( 주로 저장 )
    public BoardEntity toEntity(){
        return BoardEntity.builder()
                //.bno(bno)  저장 용도라면 bno는 자동이라서 생략 가능하다.
                .btitle(btitle).bcontent(bcontent).bwriter(bwriter)
                // 날짜도 자동이라서 생략 가능하다. (당연하지만자동아니면넣어야함)
                          .build();
    }

}
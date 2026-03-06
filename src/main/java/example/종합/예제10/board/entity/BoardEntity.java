package example.종합.예제10.board.entity;

import example.종합.예제10.board.dto.BoardDto;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor @AllArgsConstructor @Data @Builder
@Entity @Table(name = "board")
public class BoardEntity extends BaseTime{

    @Id @Column( name = "게시물번호")
    @GeneratedValue( strategy = GenerationType.IDENTITY ) // auto_increment
    private Integer bno;
    @Column( name = "제목", length = 255 , nullable = false ) // not null
    private String btitle;
    @Column( name = "내용", columnDefinition = "longtext not null") // 자바에 longtext가 없어서 직접 sql 설정.
    private String bcontent;
    @Column( name = "작성자", length = 30 , nullable = false ) // not null
    private String bwriter;
    // 수정+생성날짜는 BaseTime으로 상속받기

    // entity --> dto ( 주로 조회 )
    public BoardDto toDto(){
        return BoardDto.builder()
                .bno(bno).btitle(btitle).bcontent(bcontent).bwriter(bwriter)
                .createDate(getCreateDate().toString()).updateDate(getUpdateDate().toString())
                       .build();
    }
}
package springweb.board.entity;

import jakarta.persistence.*;
import lombok.*;
import springweb.board.dto.BoardDto;
import springweb.member.entity.MemberEntity;

@Entity @Table(name = "board")
@NoArgsConstructor @AllArgsConstructor @Data @Builder
public class BoardEntity extends BaseTime{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno; // 게시물 번호
    @Column( nullable = false , length = 255 )
    private String btitle; // 게시물제목 // 게시물 제목
    @Column( nullable = false , columnDefinition = "longtext")
    private String bcontent; // 게시물 내용
    @Column( nullable = true , length = 255 )//columnDefinition = "longtext") // 주로 첨부파일은 파일 자체를 저장하는 게 아니라, 파일의 위치(서버 내 경로)를 저장한다.
    private String bfile; // 게시물 참부파일. 만약에 게시물 당 첨부파일 여러 개면 엔티티 분리 필요.

    // 단방향 entity 추가: 한 명의 회원이 여러 개 게시물 작성. 1:N
    @ManyToOne
    @JoinColumn(name = "mno")
    @ToString.Exclude
    private MemberEntity memberEntity;

    public BoardDto toDto(){ // 주로 조회용
        return BoardDto.builder()
                            .bno(this.bno).btitle(this.btitle).bcontent(this.bcontent).bfile(this.bfile)
                            .createDate(getCreateDate().toString()).updateDate(getUpdateDate().toString())
                            .mno(memberEntity.getMno()).mname(memberEntity.getMname())
                        .build();
    }
}

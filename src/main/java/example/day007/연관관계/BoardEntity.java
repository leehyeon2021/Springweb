package example.day007.연관관계;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data @Builder
@Entity @Table(name = "board")
public class BoardEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bno;

    private String bcontent;

    // ⭐ 단방향 FK 만들기
        // 관례적으로 fk 필드명도 pk 필드명과 동일하게 한다. (cno_fk라고 해도 되긴 함!)
    @JoinColumn(name = "cno")
        // @ManyToOne 다수가 하나에게, 1:M. 하나의 카테고리에 여러 개 게시물 참조
    @ManyToOne
    private CategoryEntity categoryEntity;
}
/* 생성됨
    create table board (
            bno integer not null auto_increment,
            bcontent varchar(255),
            primary key (bno)
            foreign key (cno) references category (cno)
        ) engine=InnoDB
 */
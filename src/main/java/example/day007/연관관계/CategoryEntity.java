package example.day007.연관관계;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor @Data @Builder
@Entity @Table( name = "category" )
public class CategoryEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer cno;
    private String cname;

    // ⭐ 양방향 (데이터베이스에 존재하지 않는다.) (순환형데이터베이스는적절하지않대)
    // - 단방향은 Fk에서 필수인데 양방향은 선택이다. 양방향은 Join할 필요가 없어서 편하다. 다만 복잡도가 증가하는 설계이기에 대규모에서는 적합하지 않다.
    @OneToMany(mappedBy = "categoryEntity") // 하나가 다수에게. 1:M. 자바에서만 사용!!(데이터베이스 설계에 참여 안 함(DB에서 볼 때는 안 나타남). 설계에선 안 하고 자바에서만 mapping하겠다.) (자바에서만)조회할 때만 사용!
        // mappedBy = "매핑할FK필드명(Column말고 그냥 객체명. 필드명!)"
    @ToString.Exclude // 순환참조방지
    @Builder.Default // new 생성자 대신에 빌더로 객체 생성시 초기값 사용.
    private List<BoardEntity> boardEntityList = new ArrayList<>();
}
/* 생성됨
    create table category (
            cno integer not null auto_increment,
            cname varchar(255),
            primary key (cno)
        ) engine=InnoDB
 */
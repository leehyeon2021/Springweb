package example.day007.연관관계;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor@NoArgsConstructor@Data@Builder
@Entity @Table(name = "reply")
public class ReplyEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rno;
    private String rcontent;

    // 단방향 Fk 만들기
    @ManyToOne( cascade = CascadeType.ALL , fetch = FetchType.LAZY ) // FK 제약조건 설정
    @JoinColumn(name = "bno") // FK 필드명 설정
    private BoardEntity boardEntity;

}

/*
- 영속성: 자바객체와 데이터베이스 데이터 간 매핑/연결 상태
- cascade 속성: PK와 FK 제약조건 옵션
   - `CascadeType.ALL` : 부모가 삭제/수정/저장되면 자식도 같이 삭제/수정/저장되어 반영한다.
   - `CascadeType.REMOVE` : 부모가 삭제되면 자식도 같이 삭제되어 반영한다. (on delete cascade)
   - `CascadeType.MERGE` : 부모가 수정되면 자식도 같이 수정되어 반영한다. (on update cascade)
   - `CascadeType.DETACH` : 부모가 영속해제하면 자식도 같이 영속해제된다.
      - 영속성 해제: 자바객체외 데이터베이스 데이터 간 매핑/연결 해제. 지속성을 없앤다는 뜻. 연결 끊겠다.
   - `CascadeType.REFRESH` : 부모가 재호출(갱신)되면 자식도 같이 재호출(갱신)한다.
   - `CascadeType.PERSIST` : 부모가 저장되면 자식도 같이 저장된다.
- fetch 속성: 부모(PK) 조회 시 자식(FK) 관계에서 엔티티 조회여부 선택할 수 있다.
   - `FetchType.EAGER` : (기본값) 해당 엔티티 조회 시 참조 엔티티 모두 즉시 조회한다. (필요하지 않아도 모두 조회)
      - 특징: 초기 로딩이 느리다. 재사용 시 빠르다. 불필요한 정보까지 있을 경우 성능 저하.
   - `FetchType.LAZY` : 해당 엔티티 조회 시 참조 엔티티는 조회하지 않는다. (참조 엔티티 호출 시 조회!!) (필요한 것만 조회)
      - 특징: 초기 로딩이 빠르다. 재사용 시 느리다. **필요한 정보까지만 적절하게 사용한다.** **지연로딩**

- 단방향/양방향 활용
   - 만약에 1번 카테고리에 게시물을 등록한다면: **fk 필드에 fk값이 아닌 fk엔티티를 대입한다.**
```java
BoardEntity saveEntity = new BoardEntity();
// saveEntity.setCategory( 1 );                   // (X)
Category category = repository.findById(1).get(); // (O)
saveEntity.setCategory( category );               // (O)
repository.save( saveEntity );
```
   - 만약에 3번 게시물에 댓글 등록한다면: **fk필드에 fk값인 3 대신 3을 갖는 fk엔티티를 찾아서 대입한다.**
```java
ReplyEntity saveEntity = new ReplyEntity();
BoardEntity board = repository.findById( 3 ).get();
saveEntity.setBoardEntity( board );
repository.save( saveEntity );
```
 */
package example.day006.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @Data @NoArgsConstructor @Builder // 롬복
@Entity @Table(name = "goods")// 영속성 관련

public class GoodsEntity extends BaseTime{ // 상속

        // JPA에는 @Id가 없으면 실행조차 안 된다.
    @Id // JPA는 엔티티 내 1개 이상의 primary key를 필수로 한다.
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer gno;    // 제품번호

    @Column( name = "제품명" , nullable = false , length = 100) // , unique = true , insertable = false , updatable = false)
    private String gname;   // 제품명

    //@Column // 생략 가능(생략해도 테이블에 생성은 됨). 자바의 타입 -> SQL 타입. 자바의 변수명 -> SQL 필드명
    private Integer gprice; // 제품가격

    @Column( columnDefinition = "varchar(100) default '제품설명' not null")
    private String gdesc;   // 제품설명

    /*
    @Id : Primary Key
    @GeneratedValue( strategy = GenrationType.IDENTITY ) : auto_increment
    @Column( ) // 생략가능. 생략시 기본값 적용.
        name = "필드명" . 기본값은 자바필드명.
        nullable = false . 기본값은 true. (not null)
        length = 길이 . 기본값은 255. (varchar(길이))
        unique = true . 기본값은 false. (중복여부)
        insertable = false . 기본값은 true . (insert 할 때 적용여부)
        updatable = false . 기본값은 true. (update할 때 적용여부)
        columnDefinition = "sql" . JPA가 아닌 네이티브(실제SQL) 쿼리 작성할 때. (수동으로 넣기 가능. (자동도 가능하긴 한데))

     레코드 생성(회원가입/등록일/주문일/작성일 등)날짜/수정날짜 자동으로 넣어줌. JPA짱!!
     */

    // ⭐ Entity --> Dto 변환함수
    public GoodsDto toDto(){
        return GoodsDto.builder()
                .gno(this.gno).gname(this.gname).gprice(this.gprice).gdesc(this.gdesc)
                .createDate(getCreateDate().toString()).updateDate(getUpdateDate().toString()) // BaseTime에 private로 해놔서 검색 안 됨. 그래서 BaseTime에서 getter/setter 해줌.
                       .build();
    }
}
package example.day005.practice5;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // 'Entity': '본체', '실재'라는 뜻.
@Table( name = "book" ) // 테이블명. 여기서 정의 안 하면 'entity'라는 이름으로 들어간다.
@NoArgsConstructor @AllArgsConstructor @Data @Builder
public class BookEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer bid;

    @Column( name = "bname" , length = 100 , nullable = true) // 자바-카멜 bName -> mysql-대소문자구분없음 b_name => 불일치!!
    private String bname;

    @Column( name = "bwriter" , length = 50 , nullable = true)
    private String bwriter;

    @Column( name = "bpub" , length = 50 , nullable = true)
    private String bpub;

    /*
    - 주의할 점
       - mysql(db): 대소문자 구별 안 함
       - java: 대소문자 구별 함.

    - JPA에서는 Entity의 속성명과 mysql 테이블의 속성명이 일치해야 한다.
    - Entity에서 bookId 하면 ----> mysql에서 book_id로 매핑됨.
       - 방법 두 개
          1. 자바에서 카멜 사용시 mysql테이블 속성명을 _언더바 사용한다.
          2. 자바에서 카멜을 사용 안 함.
     */
}

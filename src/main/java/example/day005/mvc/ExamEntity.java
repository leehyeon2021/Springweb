package example.day005.mvc;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Entity는 데이터베이스 테이블과 객체 간 연동 목적으로 사용.

@Entity // 해당 객체는 데이터베이스 연동하겠다는 뜻
@Table( name = "exam" ) // 데이터베이스에서 테이블명 정의
@NoArgsConstructor @AllArgsConstructor @Data // 롬복
public class ExamEntity {

    @Id // Primary Key 적용하겠다는 뜻.
    @GeneratedValue( strategy = GenerationType.IDENTITY ) // auto_increment 적용하겠다는 뜻.
    private Integer eno;

    @Column( name = "ename" , length = 255 , nullable = true ) // 테이블 필드 속성.
        // nullable(허용할거?) = true(허용함)
    private String ename;

}

// Entity: 데이터베이스와 객체 간 연동 객체!!

package example.day011.todo.repository;

import example.day011.todo.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Integer> {
    // 1. JapRepository로 부터 상속받으면 .save() .find() .delete() 등등을 제공받는다.

    // * findAll() : 전체조회.
    // findById( pk값 ) : 식별자 1개 조회. 그외 추가적인 메소드는 불가능하다. 없으면 만들기.

    // ======================= 그 외 만들기 =========================

    // 2. 쿼리 메소드 : SQL 직접 작성하지 않고 추상메소드 이름으로 쿼리 자동 생성 <카멜표기법>
        // 2-1. findByTitle() : title으로 조회. 반환타입 findBy필드명( 타입 매개변수 );. 오타 나면 안 됨
    TodoEntity findByTitle( String title ); // 추상메소드란?: {} 구현부가 없는 메소드
            // 직접 만든 메소드~ㅎㅎㅎㅎ재밋당
        // 2-2. title과 content 조회. 엔티티/MAP `findBy필드명And필드명( 타입 매개변수, 타입 매개변수 );` 반환을 Map<>으로!
    Map<String,Object> findByTitleAndContent( String title , String content );

    // 3. 네이티브 메소드
        // 3-1. 연동된 데이터베이스 쿼리 사용 가능하다.
        // `@Query(value = "SQL", nativeQuery = true);`
        // `select * from 테이블명 where 속성명 = :매개변수명;` // 매개변수명 앞에 :콜론 이용하여 매개변수값 입력. (ps.setXXX() 안 해도 됨)
    @Query(value = "select*from todo where title = :title", nativeQuery = true) // nativeQuery 안 쓰면 JPQL 들어감!
    TodoEntity query1( String title ); // 매개변수: title👆
        // 3-2.
    @Query( value = "select * from tod where title = :title and content = :content", nativeQuery = true)
    Map<String,Object> query2(String title , String content);
        // 단순 검색: Map, 수정: dto/entity 이게 편하대

    // 4. JPQL

}

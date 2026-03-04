package example.day005.mvc;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service // 여긴 서비스
public class ExamService {
    @Autowired // 빈 주입 - Service는 Repository 불러옴.
    private ExamRepository examRepository;

    // R 조회 select
    public List< ExamDto > 전체조회(){

        // ⭐ select 대신에 JPA 함수 사용! (지난 시간에 조금 함) SQL 대신 JPA 함수.
            // - `findAll()` : 전체조회!!!! 대박
                // 이거 하면 select가 된 것이다.
        List<ExamEntity> examEntityList = examRepository.findAll();
        // ⭐ entity --> dto 변경. (안전상 하는 것. entity의 노출을 방지하기 위한 것.)
        List<ExamDto> examDtoList = new ArrayList<>(); // 1. 여러 개 dto 저장할 리스트 선언
        examEntityList.forEach( entity -> {  // 2. 반복문 for() vs. forEach( 반복변수 -> { 실행문;} )
            ExamDto examDto = new ExamDto();           // 3. dto 선언
            examDto.setEno( entity.getEno() );         // 4. 반복 중인 entity 객체 내 멤버변수를 dto에 저장
            examDto.setEname( entity.getEname() );
            examDtoList.add( examDto );                // 5. dto를 리스트에 저장
        });
        return examDtoList;
        // ⭐⭐ entity가 아닌 dto 반환한다. 왜? 서비스개발자만 entity 다룬다. 왜? 역할과 조작 권한 차이.
    }

    // C 쓰기 insert
    public boolean 저장( ExamDto examDto ){
        // ⭐ insert 대신에 JPA 함수 사용
            // - `save( 저장할엔티티 )` : 해당 엔티티를 insert 한다.
        // ⭐ 1. dto --> entity 변환.
        ExamEntity examEntity = new ExamEntity();
        examEntity.setEname( examDto.getEname() );
            // 2. .save 이용한 insert하고, 성공한 entity 반환 (반환: entity)
        ExamEntity savedEntity = examRepository.save( examEntity );
            // 3. 처리한 entity의 pk 여부
        if( savedEntity.getEno() >= 1 ) return true;
        return false;
    }

    // D 삭제 delete
    public boolean 삭제( int eno ){
        // ⭐ delete 대신에 JPA 함수 사용
            // - `deleteById( 삭제할PK번호 )` : 해당 pk가 존재하면 삭제.
        examRepository.deleteById( eno );  // 반환타입 void. 없음...
        return true;
    }

    // U 수정 update (update가 좀 어려움)
    @Transactional
    public boolean 수정( ExamDto examDto ){
        // ⭐ update 대신에 JPA 영속성(계속되는 성질/능력) 사용한다. (JPA에 update 함수는 딱히 없음.)
            // 영속성이라는 성질을 사용하여 -> 데이터베이스와 자바 객체가 연결되는 상태를 계속적으로 유지한다.
            // 즉: 자바 객체가 수정되면 데이터베이스도 자동으로 수정되게 한다.
        // 1. 수정할 Entity 찾기. pk로 찾기.
        Optional<ExamEntity> optional
                = examRepository.findById(examDto.getEno()); // .findById( 수정할PK번호 ) : 번호 하나 찾음. 반환타입-List
        // 2. 만약 Entity가 존재하면. .isPresent(): 조회 결과가 있으면 true, 없으면 false 반환 함수. (대박편리)
        if( optional.isPresent() ){
            ExamEntity examEntity = optional.get(); // 존재한 엔티티 꺼내기
            examEntity.setEname( examDto.getEname() ); // 입력받은(수정할) 값을 엔티티에 setter 이용하여 수정한다.
            return true;
        }
        return false;
    } // 근데 영속성 때문에 db에 반영이 안 됨?? 뭐지
    // JPA의 PUT : @Transactional  <-- 이거 없으면 수정 안 됨. true는 전달되지만 수정이 안 됨.

}

/*
1. < >: 제네릭타입 , 객체 생성할 때 타입 지정
2. Optional< > : 객체 내 null 값 제어 기능/함수 제공하는 클래스 , null에 따른 안전한 객체 사용.
      - 포장지 같은 거. present로 포장지 안을 확인하고, 안에 있는 거 가져오는 건 get.
   1. `.isPresent()` : 만약에 null이면 false 반환, 아니면 true 반환.
   2. `.get()` : 객체 반환
   3. `.orElse( null일때값 )
   4. `.orElseThrow( 예외값 )`
- 사용처 : JPA에서 `findById()` 반환 타입. 그 외 몇몇 라이브러리에 사용.
- 사용법 :
   1. Optional< 엔티티 > 변수명 = repository.findById( )
   2. 엔티티 변수명 = repository.findById( ).orElse( )
3. JPA CRUD 기본 제공
   1. `.findAll()`                : 모든 레코드/객체/엔티티 조회. 반환타입: List<엔티티명>
   2. `.findById( 조회할pk번호 )` : 특정 pk번호의 엔티티 반환. 반환타입: Optional< 엔티티명 >
   3. `.save( 저장할엔티티 )`       : 특정 엔티티를 저장(insert). 반환타입: 엔티티
   4. `.deleteById( 삭제할pk번호 )` : 특정 pk번호의 엔티티 삭제(delete). 반환타입: void
   5. 수정함수는 존재하지 않는다. < 영속성 특징 >
     - 영속성 갖는 시점: save, findAll , findById 등으로 반환된 엔티티가 바로 영속된(DB와 연결된) 엔티티이다.
     > 영속성이란?
     >: 데이터베이스와 자바객체를 연결하는 관계
     >
     >- 영속된 엔티티를 `.setter`(.set어쩌고)이용하여 객체 수정하면 자동으로 데이터베이스도 반영된다.
     >
     >- @Transactional 갖는 클래스/메소드는 여러 SQL들을 하나의 묶음으로 한 번에 처리한다.
     >   - 트랜젝션이란? : 여러 SQL들을 묶어서 하나라도 실패하면 모두 실패(Rollback), 모두 성공이면 최종성공(Commit)
     >   - 예1) 자동이체 기능: 입금 , 출금. 2개 이상의 기능을 묶은 기능.
     >      - 입금과 출금 중에 하나라도 문제가 발생하면 전체 취소.
     >   - 예2) 주문 기능: 장바구니 취소 , 입금상태확인 , 재고 차감 , 택배등록
     >      - 입금과 출금 중에 하나라도 문제가 발생하면 전체 (이체)취소
     >
     >   - 영속된 객체를 `.setter` 이용으로 여러 개 수정함으로써 여러 개의 수정(update)들을 하나로 처리한다.
     >      - 즉: 수정하는 메소드에는 @Transactional이 필수이다. (다른 건 한 번이라서 안 쓰는 것.)
 */
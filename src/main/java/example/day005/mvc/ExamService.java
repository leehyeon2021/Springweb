package example.day005.mvc;

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
            // 2. .save 이용한 insert하고, 성공한 entity 반환
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
    public boolean 수정( ExamDto examDto ){
        // ⭐ update 대신에 JPA 영속성(계속되는 성질/능력) 사용한다. (JPA에 update 함수는 딱히 없음.)
            // 영속성이라는 성질을 사용하여 -> 데이터베이스와 자바 객체가 연결되는 상태를 계속적으로 유지한다.
            // 즉: 자바 객체가 수정되면 데이터베이스도 자동으로 수정되게 한다.
        // 1. 수정할 Entity 찾기. pk로 찾기.
        Optional<ExamEntity> optional
                = examRepository.findById(examDto.getEno()); // .findById( 수정할PK번호 ) : 번호 하나 찾음. 반환타입-Optional< Entity >
        // 2. 만약 Entity가 존재하면. .isPresent(): 조회 결과가 있으면 true, 없으면 false 반환 함수. (대박편리)
        if( optional.isPresent() ){
            ExamEntity examEntity = optional.get(); // 존재한 엔티티 꺼내기
            examEntity.setEname( examDto.getEname() ); // 입력받은(수정할) 값을 엔티티에 setter 이용하여 수정한다.
            return true;
        }
        return false;
    } // 근데 영속성 때문에 db에 반영이 안 됨?? 뭐지

}

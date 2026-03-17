package example.day011.todo.service;

import example.day011.todo.dto.TodoDto;
import example.day011.todo.entity.TodoEntity;
import example.day011.todo.repository.TodoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // final 멤버변수 생성자 제공
@Transactional
public class TodoService {
    private final TodoRepository todoRepository;

    // 1. 전체조회
    public List<TodoDto> findAll(){
        // 1. 모든 엔티티를 조회한다.
        List<TodoEntity> entityList = todoRepository.findAll();
        // 2. 모든 entity -> dto로 변환
            // 방법1
        List<TodoDto> list1 = new ArrayList<>();
        for( int i = 0 ; i<entityList.size();i++){
            TodoEntity entity = entityList.get(i);
            list1.add(entity.toDto());
        }
            // 방법2
        List<TodoDto> list2 = new ArrayList<>();
        entityList.stream().forEach( entity -> { // forEach함수는 반환
            list2.add(entity.toDto());
        });
            // 방법3
        List<TodoDto> list3 = entityList.stream()       // 스트링(데이터 흐름) 시작
                .map( entity -> entity.toDto()) // 중간 연산
                .collect(Collectors.toList());              // 최종출력
            // 방법4
        List<TodoDto> list4 = entityList.stream()
                .map(TodoEntity :: toDto) // 중간연산. 람다식 대신 메소드레퍼런스API(미리 정의된 메소드)
                    // (클래스명 :: 메소드명)
                .collect(Collectors.toList());

        return list4;
    }

    // 2. 개별 조회
    public TodoDto findById( int id ){
        TodoDto todoDto = todoRepository.findById(id)
                // 방법1
//        Optional<TodoEntity> optional = todoRepository.findById(id);
//        if(optional.isPresent()){ 생략 }
                // 방법2
                //.stream() : 스트림(데이터'들'의 흐름. list에서 사용.
                    // 만약 개별이라면 Optional에서 map지원하는 거 사용. findById()의 반환타입이 Optional.)
                .map(TodoEntity :: toDto) // 중간연산
                .orElse(null);      // 만약에 조회 실패하면 null 반환.
        return todoDto;
    }

    // 3. title 개별 조회
    public TodoDto query1(String title){
        // * findById밖에 없으면 Repository에서 findByTitle 만들자.
        TodoEntity entity = todoRepository.findByTitle( title );
        TodoEntity entity1 = todoRepository.query1( title );
        return entity.toDto(); //return entity1.toDto();
    }

    // 4. title과 content 개별 조회
    public Map<String,Object> query2(String title, String content){
        // 2-2방법. 쿼리 메소드 호출
        Map<String,Object> result = todoRepository.findByTitleAndContent(title,content);
        System.out.println("result: " + result.toString());
        // 3-2방법. 네이티브 메소드 호출
        return todoRepository.query2(title,content);
    }

    // 5. title이 포함된 개별조회
    public List<TodoDto> query3(String title){
        // 2-3방법
        //List<TodoEntity> entityList = todoRepository.findByTitleContaining(title);
        // 3-3방법
        List<TodoEntity> entityList = todoRepository.query3(title);
        return entityList.stream()              // 스트링 시작
                .map(TodoEntity :: toDto)       // 중간연산. 메소드테퍼런스API
                .collect( Collectors.toList() ); // 최종출력은 List타입
    }

    // 6. Page 인터페이스란: 페이징처리 정보를 담는 인터페이스
    public Page<TodoDto> page(int page , int size){
        // 1. 페이징 옵션 설정한다. PageRequest 구현체, .of(조회할페이지번호, 페이지당개수, 정렬);
            // page-1: JPA는 페이징번호가 0부터 시작함으로써 1페이지는 0 , 2페이지는 1, 3페이지 2. 그래서page빼기1필요.
            // size: 현재 (한) 페이지에 조회할 자료/엔티티 개수
            // Sort.by( Sort.Direction.DESC , "정렬기준필드명" )
        PageRequest pageRequest = PageRequest.of( page-1 , size , Sort.by( Sort.Direction.DESC , "id")); // "id" 속성명으로 내림차순
        // 2. findXXX( pageRequest ) 구현체를 포함한다. 반환값은 Page< 엔티티 >로 들어온다.
        Page<TodoEntity> entityPage = todoRepository.findAll( pageRequest ); // 전체조회에 대한 페이징처리
            // page.content: 조회된 엔티티들(list)
            System.out.println("entityPage.getContent() = " + entityPage.getContent());             // 프론트 개발자 확인 용도
            // page.empty: 조회 실패 또는 없으면 true, 아니면 false
            System.out.println("entityPage.isEmpty() = " + entityPage.isEmpty());                   // 프론트 개발자 확인 용도
            // page.totalElements: 전체 자료 개수
            System.out.println("entityPage.getTotalElements() = " + entityPage.getTotalElements()); // 프론트 개발자 확인 용도
            // page.totalPages: 전체 페이징 개수
            System.out.println("entityPage.getTotalPages() = " + entityPage.getTotalPages());       // 프론트 개발자 확인 용도
        // 3. Page<엔티티> --> Page<Dto> 변환하기.
        return entityPage.map(TodoEntity :: toDto); // map()과 레퍼런스API를 이용한 변환 (.map(entity -> entity.toDto())해도 되긴 함)
    }

    // 7. 페이징처리2
    public Page<TodoDto> page2(String keyword , int page , int size){
        // 1. 페이징 옵션(구현체) 만든다.
        PageRequest pageRequest = PageRequest.of(page-1,size,Sort.by(Sort.Direction.DESC,"id"));
        // 2. 전체조회인지 키워드 조회인지
        Page<TodoEntity> result; // 조회 결과 담는 Page 타입
        if(keyword == null || keyword.isBlank()){ // 만약 키워드가 비어있으면 전체조회
            result = todoRepository.findAll( pageRequest );         // 전체조회 + 페이징처리
        }else{                                    // 아니면 키워드 조회
            result = todoRepository.query4( keyword , pageRequest); // 개별조회메소드생성 + 페이징처리
        }
        return result.map(TodoEntity::toDto);
    }
}

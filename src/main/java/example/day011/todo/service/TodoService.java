package example.day011.todo.service;

import example.day011.todo.dto.TodoDto;
import example.day011.todo.entity.TodoEntity;
import example.day011.todo.repository.TodoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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
    public Map<String,Object> query3(String title, String content){
        // 2-2. 쿼리 메소드 호출
        //return todoRepository.findByTitleAndContent(title,content);
        // 3-2. 네이티브 메소드 호출
        return todoRepository.query2(title,content);
    }

}

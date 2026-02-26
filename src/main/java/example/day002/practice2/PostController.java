package example.day002.practice2;

// [문제 1]

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// @Component // 싱글톤 대신에 스프링컨테이너 빈(객체) 등록
// @Controller // + 위에서 HTTP 기능( GET/POST/PUT/DELETE )
// @ResponseBody // + 위에서 HTTP 응답 Content-Type 자동 설정
@RestController // 위 세 개 다 있는 것

@RequestMapping("/practice2/post")
public class PostController {

    /*
    메소드: 상호작용(주고받는 쌍방향 행동)
       - 매개변수: 매개(중개)변수(정해져있지않은값)
                : 메소드가 호출될 때 들어오는 값(인수)
       - 반환값: 메소드가 종료될 때 호출했던 곳으로 반환해주는 값(리턴)
    HTTP: 상호작용( request/response )
       - request: 클라이언트가 서버로 부터 요청
       - response: 서버가 처리한 결과를 클라이언트에 반환/응답
     */

    // 1. 글쓰기
    @PostMapping
    public boolean POST(){ return true; }

    // 2. 전체 글 조회
    /* 컬렉션 프레임워크
       - [ ] 1개 == List 1개
       - { } 1개 == Map 1개
          - Map: key와 value 한 쌍. 엔트리를 갖음. 여러 개 엔트리를 저장한다. DTO와 JSON 대용으로 사용.
          - List< PostDto > vs. List<Map<String,Object>>    */
    @GetMapping
    public List< Map< String, Object> > GET1( ){

        List< Map< String, Object> > list = new ArrayList<>();

        Map< String, Object > map1 = new HashMap<>();
        map1.put("bno" , "1" );        map1.put("btitle" , "제목1");
        list.add( map1 );

        Map< String, Object > map2 = new HashMap<>();
        map2.put("bno" , "2" );        map2.put("btitle" , "제목2");
        list.add( map2 );

        return list;
    }

    // 3. 개별 글 조회
    @GetMapping("/view")
    public Map<String,Object> GET2(){

        Map<String,Object> map1 = new HashMap<>();

        map1.put("bno" , "1" );        map1.put("btitle" , "제목1");

        return map1;
    }

    // 4. 개별 글 수정
    @PutMapping
    public boolean PUT(){
        return true;
    }

    // 5. 개별 글 삭제
    @DeleteMapping
    public int DELETE(){
        return 3;
    }


}

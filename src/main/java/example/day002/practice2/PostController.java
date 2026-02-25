package example.day002.practice2;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/practice2/post")
public class PostController {

    // 1. 글쓰기
    @PostMapping
    public boolean POST(){ return true; }

    // 2. 전체 글 조회
    @GetMapping
    public List GET1( ){
        List<Map<String,String>> list = new ArrayList<>();
        Map<String,String> map1 = new HashMap<>();
        map1.put("bno" , "1" );
        map1.put("btitle" , "제목1");
        list.add( map1 );
        Map<String,String> map2 = new HashMap<>();
        map2.put("bno" , "2" );
        map2.put("btitle" , "제목2");
        list.add( map2 );
        return list;
    }

    // 3. 개별 글 조회
    @GetMapping("/view")
    public Map GET2(){
        Map<String,String> map1 = new HashMap<>();
        map1.put("bno" , "1" );
        map1.put("btitle" , "제목1");
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

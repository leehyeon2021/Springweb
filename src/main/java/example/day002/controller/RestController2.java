package example.day002.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller // HTTP 기능 + 빈 등록
public class RestController2 {

    // 1. 100 반환하는 메소드
    @GetMapping("/day002/task") // was(톰캣)주소/내가정의한주소, localhost:8080/day002/task
    @ResponseBody // Response(응답) Body(객체지향) : 응답 자료 자동 타입 변환,
    // java(객체지향) <--번역--> HTTP(문자) , JAVA는 INT를 반환하겠다고 하지만 HTTP는 INT 모른다.
    // 즉] JAVA 타입을 자동으로 HTTP contents Type 변환해준다. int -> application/json
    public int method1(){
        System.out.println("RestController2.method1");
        return 100;
    }

    // 2. 문자열 반환하는 메소드
    @GetMapping("/day002/task2") @ResponseBody // java String -> text/plain
    public String method2(){
        System.out.println("RestController2.method2"); return "유재석";
    }

    // 3. Map 타입 반환하는 메소드
    @GetMapping("/day002/task3") @ResponseBody // java Map -> application/json
    public Map< String , Object > method3(){
        Map< String , Object > map = new HashMap<>();
        map.put("유재석", 100);    map.put("강호동", 90);
        return map;
    }

    // 4. boolean 타입 반환하는 메소드
    @GetMapping("/day002/task4") @ResponseBody // java boolean -> application/json
    public boolean method4(){
        return true;
    }

    // 5. DTO 타입 반환하는 메소드
    @GetMapping("/day002/task5") @ResponseBody // java DTO -> application/json
    public TaskDto method5(){
        TaskDto taskDto = new TaskDto();
        taskDto.name = "유재석"; taskDto.point = 100;
        return taskDto;
    }
}

class TaskDto{
    String name; int point;
    // getter setter가 있어야 Spring이 DTO의 객체를 찾을 수 있다! (없으면 빈 깡통 나옴)
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getPoint() {  return point;  }
    public void setPoint(int point) { this.point = point; }
}
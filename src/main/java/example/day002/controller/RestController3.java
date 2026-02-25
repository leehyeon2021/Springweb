package example.day002.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

// @Component // 빈 등록
// @Controller // + HTTP 통신 기능            = 사용처 : view(화면) 반환
@RestController // + ResponseBody 포함        = 사용처 : 값(JSON(String 빼고)) 반환

// @RequestMapping("/공통URL") 해당 컨트롤러 내 메소드들이 사용하는 공통 URL 정의
@RequestMapping("/day002")

public class RestController3 {
    // 1. GET 클래스가 @RestController를 사용하므로 @ResponseBody를 생략해도 된다. 그냥 @Component나 @Controller 사용할 때만 @ResponseBody.
        // * 클래스가 @RequestMapping("/공통URL") 가지므로 localhost:8080/day002/~ 일케 됨. 그래서 @GetMapping("/task6")만 해도 됨.
    @GetMapping("/task6")
    public String method1(){ return "서버에게 받은 메시지";}

    // 2. GET ( http://localhost:8080/day002/task7?name=홍길동&age=15 )
    @GetMapping("/task7") // 쿼리스트링이란? URL(웹주소) 뒤로 ?물음표 작성 후 매개변수명1=값1&매개변수명2=값2
    public int method2( @RequestParam String name , @RequestParam int age ){ // 쿼리스트링매개변수명 == 메소드매개변수명 -> 이래야 자동맵핑 됨!
        // @RequestParam 이란? URL(웹주소) 안에 포함된 쿼리스트링 매개변수 값을 가져오는 어노테이션. // 생략 가능!
        System.out.println("RestController3.method2");              // soutm: 메소드명 출력 자동완성
        System.out.println("name = " + name + ", age = " + age);    // soutp: 메소드 매개변수 출력 자동완성
        return age;
    }

    // 다 시 하 기 !!!!!!!!!!!
    // 녹 강 보 기 !!!!!!!!!!!!!!!!!!!

    // 3. GET ( http://localhost:8080/day002/task8?age=40 )
    @GetMapping("/task8")
    public int method3( @RequestParam( required = false ) String name , @RequestParam( name = "age" ) int 나이 ){ // <- age라는 이름(name)을 '나이'로 표시하겠다. 라는 뜻. 근데 보통 이렇게 안 하고 그냥 같게 함.
        // 만약에 쿼리스트링의 매개변수명과 자바의 매개변수명이 다르면 @RequestParam( name = "쿼리스트링매개변수명")
        // 만약에 쿼리스트링의 매개변수명을 필요로 하지 않을 경우 @RequestParam( required = false ). 기본값은 true (위에서 name값이 task8?age=40 없음. 없어도 됨.하고 알려주는 거.)
        System.out.println("RestController3.method3");
        System.out.println("name = " + name + ", 나이 = " + 나이);
        return 나이;
    }

    // 4. DELETE ( http://localhost:8080/day002/task9?name=홍길동 )
    @DeleteMapping("/task9")
    public int method4( String name , @RequestParam( defaultValue = "19") int age ){
        // 만약에 @RequestParam 생략해도 매개변수 매핑(연결) 가능하다.
        // 만약에 쿼리스트링에 매개변수명이 존재하지 않을 때 기본값으로 설정하기. @RequestParam( defaultValue="초기값" )
        System.out.println("RestController3.method4");
        System.out.println("name = " + name + ", age = " + age);
        return age;
    }

    // 5. DELETE ( http://localhost:8080/day002/task10?name=홍길동&age=15 )
    @DeleteMapping("/task10")
    public int method5(@RequestParam Map< String , Object> map ){
        System.out.println("RestController3.method5");
        System.out.println("map = " + map); // map = {name=홍길동, age=15}
        return 10;
    }

    // 6. POST ( http://localhost:8080/day002/task11?name=홍길동&age=15 )
    @PostMapping("/task11")
    public int method5( @ModelAttribute ExamDto examDto){ // -> @@RequestParam 하면 오류 남.
        System.out.println("RestController3.method5");
        System.out.println("examDto = " + examDto);
        return 6;
    }

    // 즉1) URL?매개변수=값 방식인 쿼리스트링 URL상 매개변수 노출이 된다.
    // * GET/DELETE -> 쿼리스트링 -> @RequestParam/@ModelAttribute
    // * POST/PUT -> +BODY본문+ -> @RequestBody
    // 즉2) URL 상의 매개변수 노출을 가리기 위해 BODY(본문) 사용하자.
    //      - 개인정보/패스워드/민감한 정보들은 POST/PUT BODY(본문) 사용하자.
    // 예시) 편지의 편지봉투(껍데기. 누구나 봄) = 쿼리스트링 , 편지의 내용물(편지지. 주인만 봄) = BODY

    // 7. BODY 적용해보기 ( http://localhost:8080/day002/task12 )
    // (탈랜드임->) BODY에다가 JSON 형식으로 {"name" : "홍길동" , "age" , "15"}
    @PostMapping("/task12")
    public String method6( @RequestBody ExamDto examDto){
        System.out.println("RestController3.method6");
        System.out.println("examDto = " + examDto);
        return "7. 우왕";
    }
        // HTML --> JS --> JAVA( controller -> dao )

    // 8. http://localhost:8080/day002/task13
    @PutMapping("/task13")
    public int method7( @RequestBody Map< String , Object > map){
        System.out.println("RestController3.method7");
        System.out.println("map = " + map);
        return 13;
    }

}
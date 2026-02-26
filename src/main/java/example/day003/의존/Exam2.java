package example.day003.의존;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class Exam2 {}

/* IOC , DI , Bean , Container

- IOC (Inversion Of Controller)
: 제어의 역전.
   - 개발자가 직접 해야하는 객체 제어권을 스프링에게 위임
   1. 정의
   : 객체의 생성과 관리의 **제어권**을 개발자가 아닌 스프링에게 위임한다.
   2. 스프링에서 스프링 **컨테이너**를 담당한다.
   3. 목적
   : 개발자에게 편의성, 규칙성(협업 간의 객체 충돌 방지)에 따른 관리
   4. 주요 어노테이션 (아래는 외워두기)
      - @Component
      : 스프링 컨테이너에 클래스(Bean빈)의 정보를 등록한다.
      - Spring MVC에 내장된 어노테이션
      : @Controller , @RestController , @Service , @Respository , @Configuration 등등
      > 즉, MVC 패턴은 IOC 기반이면서 필수이다.
   - 용어
      - Bean 빈
      : 객체
      - Component 컴포넌트
      : 재사용 가능한 모듈
        - Module 모듈
        : 최소한의 기능 단위

- DI (Dependency Injection)
: 의존성 주입
   - IOC를 표현하기 위한 기술 중 하나.
   - 위임 받은 객체 제어권을 사용하여 객체 가져오기.
   1. 정의
   : 객체를 직접 생성하지 않고 외부(스프링 컨테이너)로 부터 객체를 주입받도록 하는 방법
   2. 목적
   : 객체들 간의 결합도(관계)를 낮추고 유지보수성을 향상.
   3. 주요 어노테이션
      - @AutoWired
      : 스프링 컨테이너에 등록된 빈(Bean, 객체)를 꺼내온다.

### 다른 클래스의 메소드를 호출하는 방법 (Spring이 얼마나 편한지 배우는 중)
1. 직접 객체와 메소드를 관리한다. (자바 기초)
``` */
class TaskDao1{ void method1(){} }
class TaskController1{
    void method(){
        TaskDao1 taskDao1 = new TaskDao1(); // 객체 생성
        taskDao1.method1(); // 해당 객체 내 메소드 호출
    }
}
/*```

2. static을 써서 객체 없이 사용한다.
``` */
class TaskDao2{ static void method1(){} }
class TaskController2{
    void method(){
        TaskDao2.method1(); // static 메소드를 사용하면 객체 없이 클래스명.메소드명로 호출 가능.
    }
}
/*```

3. MVC 싱글톤
``` */
class TaskDao3{
    private TaskDao3(){}
    private static TaskDao3 instance = new TaskDao3();
    public static TaskDao3 getInstance(){return instance;}
    void method1(){}
}
class TaskController3{
    void method(){
        TaskDao3.getInstance().method1();
    }
}
/*```

4. Spring에서 하는 법 (이거 쓰세용)
: 컨테이너 사용.
``` */
@Component // 스프링 컨테이너 빈 등록 (라벨/스티커/사용법. 얘는 Component입니다. 하고 알려주는 라벨이라고 보면 된다.)
class TaskDao4{ void method1(){} }
class TaskController4{

    // DI 방법1
    // 해당하는 변수에 스프링 빈에 등록된 객체를 주입해준다.
    //@Autowired
    //private TaskDao4 taskDao4; //new 하면 새롭게 만드는 거라서 안 됨. (밑이랑 이름 같아서 오류 나길래 주석처리 함.)
    // DI 방법2 (⭐권장)
    private final TaskDao4 taskDao4; // final은 수정 불가능한 키워드. 중간에 주입 받은 값이 바뀌지 않도록. 단, 초기화 필수.
    @Autowired // 생성자에 써서 final로 하는 게 권장된다. 근데 그냥 이렇게 해도 된?다? ...
    public TaskController4( TaskDao4 taskDao4){
        this.taskDao4 = taskDao4;
    }

    void method(){
        taskDao4.method1();
    }
}
package example.day003.어노테이션;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

class SuperClass{ void method(){} } // 상위 클래스
class SubClaas extends SuperClass{  // 하위클래스
    @Override             // 메소드 재정의를 알리는 어노테이션
    void method(){}       // 상위 클래스로 부터 물려 받은 메소드를 재정의한다.
    @Deprecated         // 더 이상 사용하지 않는 메소드를 알리는 어노테이션
    void method2(){}    //
}




// [ 어노테이션 만들기 ] : 인터페이스 기반
@Retention(RetentionPolicy.RUNTIME) /* 해당 어노테이션이 실행되는 시점
@Retention( ) : 어노테이션 생성주기(유지기간)을 설정
@Retention( RetentionPolicy.RUNTIME ) // 런타임(실행중)까지 유지 뜻
@Retention( RetentionPolicy.CLASS ) // .class 파일에 포함 뜻 , 실행이 아닐때 유지 뜻
@Retention( RetentionPolicy.SOURCE ) // 컴파일 이후에 삭제 한다는 뜻 */

@Target(ElementType.METHOD) /* 해당 어노테이션을 주입할 코드
@Target( ) : 어노테이션 적용할 수 있는 위치(대상)을 설정
@Target( ElementType.METHOD ) : 메소드에서 사용 뜻
@Target( ElementType.TYPE ) : 클래스,인터페이스 등등 타입 사용 뜻
@Target( ElementType.FIELD ) : 멤버변수 사용 뜻
@Target( ElementType.PARAMETER ) : 매개변수  사용 뜻
@Target( ElementType.CONSTRUCTOR ) : 생성자  사용 뜻*/

@interface MyAnnotation{ // 내가 만든 어노테이션! 을 사용하기 위해 위의 @Retention과 @Target 만듦.
    String value1(); // 구현부가 없는 추상 메소드
}

// [ 어노테이션 사용하기 ] : 내가 만든 어노테이션 사용
class TestClass{
    @MyAnnotation( value1 = "주입하는 값") // @어노테이션이란?: 해당 기술을 사용하기 위한 설명
    public void method(){} // 메소드 : 만약에 해당 메소드가 실행되면
}



public class Exam1 {
    public static void main(String[] args) {

/*
## 스프링 기능이 아닌 자바의 어노테이션 설명
- 어노테이션 개념 익히기

### `@`: 어노테이션
1. 정의
: 자바에서 코드의 메타데이터를 추가하는 문법.
   - 어노테이션은 인터페이스 기반. 방법을 제공.
   - 메타데이터란?
   : 특정한 데이터를 설명하는 데이터

2. 목적
   1. 메타데이터 제공
   2. 코드 간소화
   3. 가독성 향상

3. 종류
   1. 표준 어노테이션
   : 자바에서 기본적으로 제공하는 어노테이션
      - @Override
      : 상위 클래스로 부터 메소드를 재정의
   2. 메타 어노테이션
   : 코드를 정의하거나 동작 제어할 때 사용되는 어노테이션
      - 스프링 프레임워크에서 사용. (@RequestBody 같은 거)
```
class SuperClass{ void method(){} } // 상위 클래스
class SubClaas extends SuperClass{  // 하위클래스
    @Override             // 메소드 재정의를 알리는 어노테이션
    void method(){}       // 상위 클래스로 부터 물려 받은 메소드를 재정의한다.
    @Deprecated         // 더 이상 사용하지 않는 메소드를 알리는 어노테이션
    void method2(){}    //
        }
```

4. 어노테이션 만들기 (위에 @interface 만든 거 참고)
   1. @interface 어노테이션{}
   : 어노테이션은 인터페이스 기반.
   2. 인터페이스 위에 실행 시점, 실행 위치 설정.
      1. @Retention( RetentionPolicy.RUNTIME ): 프로그램 실행 중에 유지하겠다는 뜻
      @Retention( RetentionPolicy.CLASS ): .class 파일에 포함한다는 뜻.
      @Retention( RetentionPolicy.SOURCE ): 컴파일( 코드->번역 ) 까지만 유지한다는 뜻.
      2. @Target( ElementType.TYPE ) : 클래스에서 사용
      @Target( ElementType.METHOD ) : 메소드에서 사용 뜻
      @Target( ElementType.FIELD ) : 멤버변수 사용 뜻

5. 스프링 어노테이션
```
       개발자 입장                        스프링 프레임워크
       @RestController -------------> @RestController 추상화한 구현체(클래스)들이 자동 실행 ( 동적 로딩 )
```
   - 인터페이스란?: 사용자와 시스템 사이에서 중개 해주는 애

6. 동적 로딩이란?
: 리플렉션, 실행 중에 클래스를 읽어와서 객체를 생성하는 과정.
   - 예시)성 `Class.forName( "com.mysql.cj.jdbc.Driver)`: jdbc연동에 필요한 Driver객체를 생성한다.

 */

    }
}

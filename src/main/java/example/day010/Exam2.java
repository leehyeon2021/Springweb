package example.day010;

// 자바 문법 다시 보기

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

interface Calculator{
    // 추상메소드: 구현부{}갸 없는 메소드
    int plus( int x , int  y );
}

public class Exam2 {
    public static int plus( int x , int y ){ return x+y; }
    public static void main(String[] args) {
        // 1. 메소드 호출
        int result1 = plus( 5 , 2 ); // 7
        System.out.println("result1 = " + result1);

        // 2. 익명구현체:
            // 구현체: 추상메소드를 구현한 객체
            // 익명구현체: 클래스 없이 구현한 인스턴스
        //인터페이스명 변수명 = new 인터페이스명(){ 오머라이딩 };
        Calculator cal2 = new Calculator() { // 원래는 interface는 new객체 못 만듦.
            @Override public int plus(int x, int y) { // 재정의
                return x-y;
            }
        };
        int result2 = cal2.plus( 5 , 2 ); // 3
        System.out.println("result2 = " + result2);

        // 3. 람다 표현식: (매개변수)->{구현부} ,
            // 자바 8부터 가능. 타입명, 함수명 생략할 수 있어 편함.
        Calculator cal3 = (x, y) -> x - y;
        int result3 = cal3.plus( 5 , 2 ); // 3
        System.out.println("result3 = " + result3);
            // forEach() , map() 함수도 있음~

        // 4. 람다 표현식을 사용하는 **함수형** 인터페이스
            // <>: 제네릭. 인스턴스(객체)를 생성할 때 인스턴스(객체) 내 멤버들의 타입을 정의한다.
                // List<Integer> : List 객체 생성시 내부에 Integer 타입으로 항목을 구성하겠다는 뜻
                // Map<String,Object> : Map 객체 1개 생성시 내부에 String 타입으로 key, object 타입으로 value를 구성하겠다는 뜻.
                // 즉 제네릭이란, 객체 내 멤버들의 타입을 매개로 정할 수 있는 타입
        // 4-1. Function< T , R > : T는 입력받은 값, R은 결과 반환값
            // .apply( T ) 메소드 사용
            // map()은 Function을 사용함.
        Function<Integer,Integer> function = (x ) -> { return  x * 2 ;}; // return 생략 시 {}도 같이 생략해주면 된다.
        System.out.println("function.apply(3) = " + function.apply(3)); // 6
        // 4-2. Supplier< T > : T밖에 없음. 입력받은 값 없음. 입력 없이 반환만 함.
        Supplier<Double> supplier = ( ) -> 3.14; // return 생략 시 {}도 같이 생략.
        System.out.println("supplier.get() = " + supplier.get()); // 3.14
        // 4-3. Consumer< T > : T: 입력받은값타입. 결과값 없음(return불가). 반환 없이 입력만 함.
            // .accept( T ) 메소드 사용
        Consumer<String> consumer = (str) -> {System.out.println(str);};
        consumer.accept("홍길동");
            // **forEach()는 Consumer기반. 그래서 return 불가.**
        // 4-4. Predicate< T > : T: 입력받아서TRUE/FALSE 반환. 입력 하면 boolean 반환함.
        Predicate< Integer > predicate = (x) -> {return x%2==0;}; // 중괄호 할 거면 return넣기, 안 할거면 return빼기.
        System.out.println("predicate.test(3) = " + predicate.test(3)); // false

    }
}

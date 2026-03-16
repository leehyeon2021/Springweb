package example.day010;


class Vip{
    // 2-3. 오버라이딩
    int add3( int a , int b ){ return a+b; }
}
class Member extends Vip {
    // 2-1. 인스턴스
    int add( int a , int b ){ return a+b; }
    // 2-2. static
    static int add2( int a , int b ){ return a+b; }
    // 2-3. 오버라이딩
    int add3( int a , int b ){ return a-b; }
}

public class Exam1 {
    public static void main(String[] args) {

        // 1. 함수 = 메소드 == 기능 == 방법
        // 2. 함수 종류
            // 2-1. 인스턴스(멤버 메소드) == new(인스턴스화) 이용하여 인스턴스 생성하여 함수 호출
        Member member = new Member();
        int result1 = member.add( 3, 4 );
            // 2-2. static(정적메소드) == 인스턴스(new) 생성 없이 함수 호출
        int result2 = Member.add2( 3 , 4 );
            // 2-3. 오버라이딩 == 상속 또는 구현 받은 타입의 메소드 재정의
        Member member3 = new Member();
        int result3 = member3.add3( 4, 3 );
        // 3. 함수 기본 구조  **자바는 매개변수/반환값 타입 일치** ??
            // 매개변수 == 인자값을 저장하는 변수 == 함수 안으로 들어오는 값
            // 반환값 == 함수를 호출했던 곳으로 되돌려주는 값
        int insult4 = Member.add2( 10 , 5 ); // 10과 5를 인자값이라고 한다. / result4에는 15라는 반환값이 있다.
        int insult5 = Member.add2( 15 , 3 ); // 15과 3를 인자값이라고 한다. / result5에는 18이라는 반환값이 있다.
        // 4. 함수 호출하는 방법
            // ********다른 클래스일 때********
            // 4-1. 인스턴스/멤버메소드일 때
        new Member().add( 3, 4 );
            // 4-2. static 메소드일 때
        Member.add2( 3, 4 );
            // 4-3. 싱글톤 내 메소드일 때
        //Member.getInstance().add2( 3, 4 );
            // 4-4. IOC/DI 스프링 구조 메소드일 때
        //@Autowired Member member;
        //member.add5();
            // ******같은 클래스일 때******
            // 그냥 호출
            // +) static 메소드에서는 일반적인 메소드 호출이 안 된다. static 메소드일 때는 static 메소드 호출이나 인스턴스 호출 필요.

    }
}

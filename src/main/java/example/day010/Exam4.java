package example.day010;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Exam4 {
    public static void main(String[] args) {
        // 메소드 레퍼런스 API: 이미 정의된 메소드를 참조하여 사용하는 표현식
            // 1. 일반 메소드: member.add( 3, 4 )
            // 2. 람다 메소드: ( x , y ) -> { return x+y; }
            // 3. 레퍼런스 API: member :: add( 3 , 4 )
                    // 매개변수가 눈에 안 보여~~~!!~~ㅠㅠ 똑똑해지자...

        // 1. static 정적 메소드 예시
        Integer.parseInt( "10" ); // Integer.parseInt( 문자 ); 문자->정수 타입 반환 함수
            // 1-1. 레퍼런스 API로 하면:
        Function< String , Integer> function = Integer :: parseInt;
        function.apply( "10" );

        // 2. 일반 메소드 예시
        List<String> names = List.of("가나다", "라마바", "사아자", "차카타", "파하"); // 샘플
            // 2-1. for문
        for(int i = 0 ; i<names.size();i++){System.out.println("names.get(i) = " + names.get(i));}
            // 2-2. forEach문 (forEach만 할 땐 .stream() 생략 가능)
        names.forEach(x -> System.out.println("2-2: " + x));
            // 2-3. forEach문 + 레퍼런스API
        names.stream().forEach(System.out :: println); // System.out가 객체고 println가 함수.
                // map( Entity :: toDto ) entity를 dto로 변환할 때도 사용.

        // 3. 생성자(메소드) 예시
            // 3-1. for문. 리스트내 항목(이름) 하나씩 꺼내서 객체 생성.
        for(int i = 0 ; i<names.size(); i++){new Post(names.get(i));}
            // 3-2. forEach문
        names.stream().forEach(x->{new Post(x);});
            // 3-3. forEach문 + 레퍼런스API
        names.stream().forEach( Post :: new ); // "Post라는 클래스에 new 연산자를 사용하겠다."라는 뜻.
            // ** 활용
        List<Post> postList = names.stream()             // 스트림(데이터들의흐름)의 시작
                //.map( x -> {return new Post(x);})  // 람다 표현식보다는
                .map( Post :: new )                  // 메소드 레퍼런스API 방식이 짧구만
                .collect( Collectors.toList());
    }
}

class Post{
    String name;
    Post(String name){ this.name=name;} // 생성자
}

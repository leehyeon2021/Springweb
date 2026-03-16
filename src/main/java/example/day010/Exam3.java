package example.day010;

// 자바 문법 다시 보기

import java.util.List;
import java.util.stream.Collectors;

public class Exam3 {
    public static void main(String[] args) {
        // 람다표현식 , ( 매개변수 ) -> { 구현부 }
        // 스트림API: 데이터(매개변수) --> 중간연산 --> 최종출력

        /*
        스트림이란?: 데이터가 다니는 연속적인 흐름
           - 데이터들 --> 중간연산 --> 최종연산
           - 중간연산은 여러 개 가능.
           - 최종연산은 반드시 1개
        주요 연산
           - 중간 연산: .map() .filter() .sorted() .distinct() .limit()
           - 최종 연산: .forEach() .collect()

        어디서 쓰나요
           - Service에서 JPI? 할 때 사용
         */

        List<Integer> number = List.of( 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 ); // 임의의 데이터를 담고 있는 리스트 만듦

        // 1. 리스트변수명.stream().forEach( ()->{반환없음} ); (forEach는 중간연산 없이 바로 최종출력. Consumer라서 return없음)
            // 매개변수에 반복변수를 하나씩 대입하여 return 없는 반복문
        number.stream().forEach((x)->{System.out.print(x*2+"\t");}); // 2	4	6	8	10	12	14	16	18	20	 (\t는 띄어쓰기)

        // 2. 리스트변수명.stream().map( 중간연산 ).collect( ((최종출력)) Collectors.toXXX ); (map은 Function이라서 return가능. 반복return값들을 collect()로 타입 정해서 최종출력.)
            // 매개변수에 반복변수를 하나씩 대입하여 return있는 반복문
        List<Integer> result2 = number.stream().map( (x) -> { return x*2;}).collect( Collectors.toList() );
        System.out.println("\nresult2 = " + result2);

        // 3. 리스트변수명.stream().map( 중간연산 ).forEach( 최종출력 );
        number.stream()
                .map( x -> x*2 )                                                // 중간연산
                .forEach( result ->System.out.println("result = " + result) );  // 중간연산 결과를 출력한다.

        // 4. 리스트변수명.stream().filter( 중간연산 ).forEach( 최종출력 );
            // .filter()는 그물망처럼 걸러서 가져와줌 (검색 기능할 때 좋지 않을까)
        number.stream()                                                // 리스트 내 데이터들의 흐름 시작
                .filter( x  -> x%2==0)                          // 중간연산 , 짝수찾기 반환한다.
                .forEach( y -> System.out.println("y: "+y) );   // 최종출력

        // 5. 리스트변수명.stream().sorted( 중간연산 ).forEach( 최종출력 );
        number.stream()                                                 // 리스트 내 데이터들의 흐름 시작
                .sorted()                                               // 중간연산, 오름차순. / 내림차순은 .sorted( Comparator.reverseOrder() )
                .forEach( y -> System.out.println("y = " + y));  // 최종출력

        // 6. 리스트변수명.stream().distinct( 중간연산 ).forEach( 최종출력 );
        number.stream()
                .distinct()                         // 중간연산, 중복 제거
                .collect( Collectors.toList() );    // 최종출력.

        // 7.리스트변수명.stream().limit( 중간연산 ).forEach( 최종출력 );
        number.stream()
                .limit( 5 )                                     // 중간연산. 앞에서부터 N개까지.
                .forEach( y -> System.out.println("y = " + y) ); // 최종출력

    }
}
/* ==================== Entity ---> Dto ================================

        일반for문

        List<BoardDto> list = new ArrayList<>();            // 여러개 dto 담는 리스트
        for( int i = 0 ; i<=entityList.size()-1; i++ ){
            BoardDto boardDto = entityList.get(i).toDto();
            list.add( boardDto );
        }

        vs. forEach문

        List<BoardDto> list = new ArrayList<>(); // 1. 여러개 dto 담는 리스트
        entityList.forEach( entity -> {          // 2. 리스트변수명.forEach( 반복변수 -> { 실행문; } );
            BoardDto boardDto = entity.toDto();  // 3. 엔티티 하나씩 dto로 변환
            list.add( boardDto );                // 4. 새로운 리스트에 담기
        } );

        vs. map()

        List<BoardDto> list = entityList.stream()
                                .map( entity -> entity.toDto() )
                                .collect( Collectors.toList() );

        vs. map() 레퍼런스API

        List<BoardDto> list = entityList.stream()
                                .map( entity :: toDto )
                                .collect( Collectors.toList() );    // 짧음!
 */

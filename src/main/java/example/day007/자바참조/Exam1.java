package example.day007.자바참조;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

public class Exam1 {
    public static void main(String[] args) {

        // 자바는 객체지향 언어이다.
        System.out.println("출력함수");
        // System: 클래스. System.out: 객체. println(): 함수/메소드
        // 즉: 특정한 객체가 특정한함수를 실행한다. // `.`: 참조

        // [1] 카테고리 객체 생성 == 클래스(설계도)로 메모리(인스턴스) 생성한다는 뜻.
        // 동일한 클래스(설계도)로 서로다른 객체를 생성한다.
        Category category1 = new Category();                    // 101호
        category1.setCno(1); category1.setCname("공지사항");
        Category category2 = new Category();                    // 102호
        category2.setCno(2); category2.setCname("자유게시판");

        // [2] 게시물 객체 생성.         // 참조란?: 다른 값에 접근한다.
        // - 즉: ORM(JPA) 기술은 이러한 객체참조를 사용해 데이터베이스의 PK,FK를 구현했다.
        // - ⭐ **단방향**: FK를 통해 PK 참조하는 것처럼, 특정한 객체를 통해 참조 객체를 참조한다.
        //   - 즉: 게시물 객체를 통해 카테고리 객체를 참조한다.
        //    1. 첫 번째 게시물은 공지사항이다.
        Board board1 = new Board();                              // 201호
        board1.setBcontent("첫 번째 게시물입니다.");
        // 첫 번째 카테고리 객체를 게시물 객체에 대입한다.
        board1.setCategory( category1 );                         // 201호 안에 101호 참조
        //    2. 두 번째 게시물은 자유게시판이다.
        Board board2 = new Board();                              // 202호
        board2.setBcontent("두번째 게시물입니다.");
        board2.setCategory( category2 );                         // 202호 안에 102호 참조

        // [3] 카테고리가 게시물을 참조
        // - ⭐ **양방향**: 두 객체 간에 서로 참조하는 관계. 데이터베이스에 존재하지 않는다.
        //   - 즉: ORM(JPA)는 단방향/양방향 모두 지원한다.
        //      - 특징: 조회가 빠르다. JOIN필요 없음. (board를 알면 category를 알 수 있다. 반대도 마찬가지.)
        // - 순환참조 문제점, 복잡한 JOIN, LAZY 지연 로딩 고려
        category1.getBoardList().add( board1 );                  // 101호에 201호 참조 대입한다.
        category2.getBoardList().add( board2 );                  // 102호에 202호 참조 대입한다.

        // 확인해보기
        // FK -> PK 단방향 참조/조회
        System.out.println( board2.getCategory() );
        // PK <-> FK 양방향 참조/조회
        // StackOverflowError: 양방향 참조 하다가 메모리 넘쳤다.
        // 해결방안: 두 객체 간의 한쪽 필드에 @ToString.Exclud 주입한다. (-> 우리는 ArrayList위에다가 @ToString.Exclude 넣어서 순환참조 방지함.)
        System.out.println( category1.getBoardList() );
    }
}

@Data // 롬복
class Category{
    private int cno;        // 카테고리 번호
    private String cname;   // 카테고리 이름
    @ToString.Exclude // 순환참조 방지.
    private List<Board> boardList = new ArrayList<>(); // 양방향 참조. 게시물목록
}

@Data
class Board{
    private int bno;
    private String bcontent;
    //@ToString.Exclude 여기다가 넣어도 됨. 참조한 둘 중 한곳에!
    private Category category; // 단방향 참조
}
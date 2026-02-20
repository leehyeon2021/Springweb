package example.종합.예제8.model.dao;

import example.종합.예제8.model.dto.BoardDto;

import java.sql.*;
import java.util.ArrayList;

public class BoardDao {
    private BoardDao(){ connect(); }                                    // ⭐ dao 생성자에서 함수를 실행한다!! -> 싱글톤이 만들어지며(객체 만들어지며) connect도 같이 실행된다.
    private static final BoardDao instance = new BoardDao();
    public static BoardDao getInstance(){return instance;}

    // ======= 데이터베이스 연동 =======
    // 1. 연동한 db서버의 정보
    private String url = "jdbc:mysql://localhost:3306/boardservice7";
    private String user = "root";   private String password = "1234";
    // 2. 연동 인터페이스 변수 선언
    private Connection conn; //선언 만! (아래 메소드 들에서 사용 많이 함.)
    // 3. 연동 함수 선언
    private void connect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver"); // mysql 라이브러리 객체 메모리 할당/불러오기
            conn = DriverManager.getConnection( url , user , password ); // mysql 구현체로 db 연동 후 연동 인터페이스에 반환.
            System.out.println("[성공] 데이터베이스 연동 성공");
        }catch (Exception e){ System.out.println("[경고] 데이터베이스 연동 실패: 관리자에게 문의"); }
    }

    // 1. 게시물 등록 DAO
    public boolean write(String bcontent , String bwriter){
        try {
            // 1. SQL 작성한다. 저장은 C에 속하는 insert. ?: 와일드카드 기호. 변수값이 들어갈 자리를 뜻한다.
            String sql = "insert into board( bcontent , bwriter) values(? , ?)";
            // 2. 연동된(Conn) 인터페이스에 내가 작성한 SQL을 기재한다. (* 일반 예외가 발생하므로 전체에 try/catch.)
            PreparedStatement ps = conn.prepareStatement(sql); // 기재: prepare , 반환: prepared
            // 3. SQL이 기재된(ps) 인터페이스에 sql매개변수 대입 , ps.set타입명( ?순서번호 , 값 );
            ps.setString( 1 , bcontent );   // == "String 타입으로 SQL 내 1번 ?에 bcontent 값을 대입(세팅)한다."
            ps.setString( 2 , bwriter );    // == "String 타입으로 SQL 내 2번 ?에 bwriter 값을 대입(세팅)한다."
            // 4. 매개변수까지 대입하여 준비가 끝났으면 SQL 실행. ps.executeUpdate() / 반환값: 반영된 레코드 수.
            int count = ps.executeUpdate();
            // 5. SQL 실행 후 반영된 레코드 수에 따른 결과 제어.
            if( count == 1 ){ return true; } // 등록된 레코드 수가 1이면 등록 성공
            else{ return false; } // 아니면 실행

            // SQL 다룰 때는 무조건 그냥 일단 try/catch 넣으면 될 듯??
        }catch (SQLException e){ System.out.println("[시스템오류] SQL 문법 문제 발생: "+e); }
        return false;
        // 들어갔는지 확인 -> SQL에서 select*from board; 하면 된다.
    }// m end

    // 2. 게시물 전체 조회 DAO
    public ArrayList<BoardDto> findAll(){
        ArrayList< BoardDto > boardDtos = new ArrayList<>();
        try{
            // 1. SQL 작성
            String sql = "SELECT*FROM board";
            // 2. SQL 기재
            PreparedStatement ps = conn.prepareStatement(sql);
            // 3. SQL 매개변수 대입은 생략. ? 없음.
            // 4. SQL 실행: 몇 개 조회했는지(executeUpdate())가 아니라, 조회 결과 테이블 제어(executeQuery())가 중요하다.
            ResultSet rs = ps.executeQuery();
                // executeUpdate(): insert,update,delete  vs.  executeQuery(): select <- 반환: ResultSet
                // ResultSet : select 결과물을 제어하는 인터페이스.
            while( rs.next() ){ // while( 논리 ){} 반복문
                // rs.next(): 조회 결과에서 다음 레코드로 1번 이동. 만약에 레코드가 10개이면 next 10번 가능.
                // rs.get타입명( 속성명 ): 현재 레코드의 속성 값을 호출한다.
                int bno = rs.getInt("bno"); // bno를 다 가져오는 것이 아니라, 현재 반복(while) 중인 첫 번째 next의 '1'을 가져온다.
                String bcontent = rs.getString("bcontent");
                String bwriter = rs.getString("bwriter");
                String bdate = rs.getString("bdate");
                // DTO 만들기
                BoardDto boardDto = new BoardDto( bno , bcontent , bwriter , bdate); //생성자 순서대로.
                // 리스트(배열) 저장
                boardDtos.add(boardDto);
            }
        }catch (SQLException e){ System.out.println("[시스템오류] SQL 문법 문제 발생: "+e); }
        return boardDtos; // 리스트를 반환한다.
    }

    // 3. 게시물 수정 DAO
    public boolean update( int bno , String bcontent ){
        try{
            // 1. SQL 작성
            String sql = "update board set bcontent = ? where bno = ?";
            // 2. SQL 기재
            PreparedStatement ps = conn.prepareStatement(sql);
            // 3. SQL 매개변수
            ps.setString( 1, bcontent ); // 첫 번째? 에는 bcontent를 넣어준다.
            ps.setInt(2 , bno ); // 두 번째 ?에는 bno를 넣어준다.
            // 4. SQL 실행
            int count = ps.executeUpdate();
            // 5. 결과
            if(count == 1){return true;} //1개 레코드가 수정되면 성공
            else{return false;} // 실패
        }catch (SQLException e){System.out.println("[시스템오류] SQL 문법 문제 발생: "+e); }
        return false; //실패
    }

    // 4. 게시물 삭제 DAO
    public boolean delete( int bno ){
        try {
            // 1. SQL 작성한다. ?는 매개변수가 들어갈 자리.
            String sql = "delete from board where bno = ?";
            // 2. 연동된[conn] 인터페이스에 SQL을 기재한다. +일반예외 있으므로 예외 처리 필요.
            PreparedStatement ps = conn.prepareStatement(sql);
            // 3. ? 와일드카드에 매개변수 대입 , ps.setXXX( ?순서번호 , 값 );
            ps.setInt( 1 , bno ); // == "1번 ?에 bno 넣어서"
            // 4. SQL 실행한다.
            int count = ps.executeUpdate(); // execute(): 실행했다 안 했다 boolean으로 알려줌. / executeUpdate(): 반영된 레코드 수까지 알려줌.
            // 5. 결과
            if(count == 1){return true;} // 삭제된 레코드 수 1개이면 성공
            else{return false;} // 실패
        }catch (SQLException e){System.out.println("[시스템오류] SQL 문법 문제 발생: "+e); }
        return false; // 실패
    }

}

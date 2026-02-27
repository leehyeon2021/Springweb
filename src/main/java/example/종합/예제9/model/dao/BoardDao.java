package example.종합.예제9.model.dao;

import example.종합.예제9.model.dto.BoardDto;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component // 빈 등록
public class BoardDao {

    // * 싱글톤은 지우되 connect는 필요하니 남겨두기. BoardDao가 들어갈 때 connect를 실행하도록.
    public BoardDao(){ connect(); }

    // ======= 데이터베이스 연동 =======
    // 1. 연동한 db서버의 정보
    private String url = "jdbc:mysql://localhost:3306/boardservice9";
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

    // 1. 전체 조회
    public List<BoardDto> findAll(){
        // 1. 조회 결과 레코드들을 DTO로 저장하기 위한 리스트 선언
        List<BoardDto> list = new ArrayList<>();
        try{
            // 2. sql 작성
            String sql = "select*from board";
            // 3. sql 등록
            PreparedStatement ps = conn.prepareStatement( sql );
            // 4. sql 실행하고 결과 받기
            ResultSet rs = ps.executeQuery();
            // 5. 첫 번째 레코드 부터 마지막 레코드까지 반복
            while (rs.next()){
                // 6. 조회 중인 레코드의 속성 정보들을 DTO로 구성
                BoardDto boardDto = new BoardDto(
                        rs.getInt("bno") , rs.getString("bcontent") ,
                        rs.getString("bwriter") , rs.getString("bdate")
                );
                // 7. 구성한 DTO를 리스트에 저장
                list.add(boardDto);
            }
        }catch ( Exception e){System.out.println(e);}
        // 8. 리스트 반환
        return list;
    }

    // 2. 게시물 등록
    public boolean write( BoardDto boardDto ){
        try{
            String sql = "INSERT INTO board( bcontent, bwriter ) VALUE(? , ?)";
            PreparedStatement ps = conn.prepareStatement( sql );
            ps.setString (1 , boardDto.getBcontent());
            ps.setString( 2, boardDto.getBwriter());
            int count = ps.executeUpdate();
            if(count==1) return true;
        }catch (Exception e){ System.out.println(e); }
        return false;
    }

    // 3. 게시물 수정
    public boolean update( BoardDto boardDto ){
        try{
            String sql = "update board set bcontent = ? where bno = ?";
            PreparedStatement ps = conn.prepareStatement( sql );
            ps.setString( 1, boardDto.getBcontent() );
            ps.setInt( 2 , boardDto.getBno() );
            int count = ps.executeUpdate();
            if(count==1) return true;
        }catch (Exception e) {System.out.println(e);}
        return false;
    }

    // 4. 게시물 삭제
    public boolean delete( int bno ){
        try{
            String sql = "DELETE FROM board where bno = ?";
            PreparedStatement ps = conn.prepareStatement( sql );
            ps.setInt( 1, bno );
            int count = ps.executeUpdate();
            if(count==1)return true;
        }catch (Exception e){System.out.println(e);}
        return false;
    }

}

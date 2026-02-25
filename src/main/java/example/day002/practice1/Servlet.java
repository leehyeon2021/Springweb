package example.day002.practice1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/*
- 서블릿 생성 방법
   1. HttpServlet으로 부터 상속 받기
   2. 서블릿 클래스 위에 @WebServlet(), 서블릿의 주소(URL) 설정하기
   3.
   4.
 */

@WebServlet("/practice1")
public class Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String value = req.getParameter("value");
        int value2 = Integer.parseInt( value );
        resp.getWriter().println(value2 + 2);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        // 1. 보내온 값 가져오기 = 요청한 정보가 들어있는 매개변수 : HttpServletRequest req
        String value = req.getParameter("value"); // HTTP는 텍스트(문자) 전송이 기본값이다. 다른 걸로 바꿀 수 없음. 무조건 String
        int value2 = Integer.parseInt( value ); // 타입 변환. 스프링에서는 자동 타입변환을 해준다.
        resp.getWriter().println(value2 * 2);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String value = req.getParameter("value");
        int value2 = Integer.parseInt( value );
        resp.getWriter().println(value2 / 2);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String value = req.getParameter("value");
        int value2 = Integer.parseInt( value );
        resp.getWriter().println(value2 % 2);
    }
}

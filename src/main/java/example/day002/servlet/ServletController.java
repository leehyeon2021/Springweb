package example.day002.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/*
- 서블릿이란?
: 자바 회사에서 HTTP 웹 동적처리를 하기 위한 라이브러리(미리 만든 거)/클래스
- 사용법
   1. 현재 클래스의 "HttpServlet" 클래스로 부터 상속 받는다.
      - 상속: 다른 클래스로 부터 멤버변수/함수 물려받는다.
   2. 현재 클래스 위에 @WebServlet("/URL") 해서 주소 설정한다. (한글제외,중복x) 영문으로 정의한다.
   3. (Spring 환경에만 해당) AppStart 클래스 위에 '@ServletComponentScan'를 주입하여 스프링이 서블릿을 찾을 수 있게 해줌.
      - Spring은 서블릿을 @RestController로 눈에 보이지 않게 사용함. 그래서 Spring에선 위를 해야 보임.
   - localhost:8080/day002/servlet <- 이렇게 크롬에 쳐보기
   - 사용처: Spring 아닌 옛날 코드(레거시 코드) 사용하는 회사는 이렇게 하기도 함. 지금처럼 동작 구조 공부할 때도 좋음.
*/
@WebServlet("/day002/servlet") // --> localhost: 8080/day00/servlet
public class ServletController extends HttpServlet {

    // * 이건 순수 자바의 메소드/함수
    boolean method( int param ){ return true;}

    // init()랑 service()는 굳이 안 해도 됨. 중요한 건 rest
    // 1. 서블릿 클래스로 부터 HTTP 요청 시 init함수가 최초로 1번 실행된다.
    @Override public void init() throws ServletException{
        System.out.println("init 함수 실행");
        super.init();
    }

    // 2. 서블릿 클래스로 부터 HTTP 요청마다 service 함수 실행된다.
    @Override public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException{
        System.out.println("service 함수 실행");
        super.service( req, res );
    }


    /*
    3. HTTP 메소드란?
    : GET 방식, POST 방식, PUT 방식, DELETE 방식으로 통신하는 방법
    - 서블릿 객체는 요청이 끝나도 사라지지 않는다. 다음 요청에 재사용.
    - HttpServletRequest(요청정보), HttpServletResponse(응답정보) 객체는 요청이 끝나면 사라진다.     */
    //   1. GET
    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //super.doGet( req, resp);
        System.out.println("ServletController.doGet"); // soutm: 현재 메소드 출력 // ServletController.doGet
        // * HTTP 요청 시 포함된 매개변수 확인
        String data = req.getParameter("data");        System.out.println("data = " + data); // data = null
        // http://localhost:8080/day002/servlet?data=서버에게  <- 이렇게 하니 콘솔에 'ServletController.doGet'(줄바꿈)'data = 서버에게' 라고 뜸
        // * HTTP 요청 시 클라이언트에게 응답
        resp.getWriter().println("Client Response : Hi !!"); // 크롬 화면에 'Client Response : Hi !!' 이렇게 뜸
    }

    //   2. POST : 브라우저(크롬)의 주소 입력창의 요청은 무조건 GET 방식 이므로 POST, PUT, DELETE는 포스트맨 vs. 탈랜드API
    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ServletController.doPost");
        String data = req.getParameter("data");        System.out.println("data = " + data);
        resp.getWriter().println("Client Response : Hi ~~");
    }

    //   3. PUT
    @Override protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        System.out.println("ServletController.doPut");
        String data = req.getParameter("data");        System.out.println("data = " + data);
        resp.getWriter().println("Client Response : Hi ++");
    }

    //   4. DELETE
    @Override protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        System.out.println("ServletController.doDelete");
        String data = req.getParameter("data");        System.out.println("data = " + data);
        resp.getWriter().println("Client Response : Hi --");
    }

    /*
    - HTTP란?
    : 클라이언트와 서버 간의 통신하는 규칙.
       - 클라이언트가 1번 요청에 1번 응답.
       즉: **요청이 없으면 응답할 수 없다.**
          - 실시간 통신 같은 경우엔 다른 것을 써야 한다. (소켓?)
    - 서블릿 클래스 내 동일한 시그니처(이름)를 갖는 메소드는 존재할 수 없다. (매개변수만 다르면 된다.-오버로딩)
       - 즉: 하나의 서블릿에 doGet() 메소드는 1개만 존재할 수 있다.
          - 그래서 전체조회/상세조회 번거로움. doGet 메소드 두 개 못 만들어서. 서블렛 두 개 만들어야 함.
          - 이런 번거로움을 방지하기 위해 스프링에선 보완한 것을 제공해줌.
             - @GetMapping , @GetMapping. 이런 식으로 두 번 사용할 수 있음. (day001에서 한 거)
     */


} // CLASS END

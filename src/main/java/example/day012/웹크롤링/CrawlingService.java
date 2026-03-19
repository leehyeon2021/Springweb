package example.day012.웹크롤링;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CrawlingService {

    // 1. Jsoup 이용한 특정 url html 정보 수집
    public List<String> test1(){
        List<String> list = new ArrayList<>(); // 여러 개 문자열 저장 리스트

        // 1. 크롤링 URL 웹페이지 주소 (크롤링할 홈페이지 찾기)
        // 페이지가 있는 홈페이지는 page URL 분석해서 반복문 돌려서 가져올 수 있음
        String url = "https://www.karnews.or.kr/news/articleList.html?sc_section_code=S1N1&view_type=sm";
        // 2. 크롤링 할 URL 요청하여 HTML 전체를 가져온다. `Jsoup.connect( 주소 ).get();` 예외 처리 필수.
            // `Document import org.jsoup.nodes.Document;`임포트
        try {
            Document document = Jsoup.connect(url).get(); // 외부 통신은 일반예외가 주로 발생
            //System.out.println("document: \n"+document);
            // 3. 특정한 마크업/요소 식별자. `document.select("식별자");`
                // 크롤링할 페이지에서 개발자도구로 크롤링할 거 제목의 클래스명 찾음
            Elements elements = document.select( " .titles > a" ); // 클래스가 titles인 마크업 아래의 <a> 가져온다.
            System.out.println("elements = " + elements);
            // 4. 여러 개 가져왔다면 반복문 이용한 요소/마크업(Element) 1개 씩 순회
            for(Element element : elements){
                // element.text(); vs. innerHTMl: 비슷하게 마크업 사이 텍스트를 반환. <a> 여기! </a>
                String title =  element.text();
                // 만약에 텍스트가 존재하면 리스트에 담기
                if( title.isBlank() ){ continue; } // 반복문 위로 이동
                else{ list.add(title); }
                // else{ list.add(title.replaceAll("/","/"))} 이렇게 나눠서 해도 됨 (replace 쓰는 법 까먹음...ㅠㅠ)
            }
        }catch (Exception e){System.out.println(e);}
        return list;
    }

    // 2. Jsoup 이용한 HTML 정보 수집 , 페이지 이동
    public List< Map<String, Object> > test2(){
        // 책 정보(dto/map)들을 담는 리스트 선언.
        List< Map<String,Object> > list = new ArrayList<>();
        try{
            for( int page = 1; page <=3 ; page++) {
                // 1. 크롤링할 URL주소 (예스24 베스트셀러 일별) + 반복문 이용하여 페이지 번호 여러 개 요청
                String url = "https://www.yes24.com/product/category/daybestseller";
                url += "?categoryNumber=001";   // 베스트셀러 카테고리 번호
                url += "&pageNumber=" +page;         // 크롤링할 페이지 번호 <page 변수로 활용>
                url += "&pageSize=24";          // 페이지당 제품 수
                // 2. URL 연결
                Document document = Jsoup.connect(url).get();
                // 3. 식별자. 단일 선택자일 때는 중복이 발생할 수도 있다.
                    // 가져올 텍스트가 위치한 식별자와 상위 식별자 1~2개 같이 선택한다. <중복 배제>
                // 책이름: .info_name > .gd_name
                Elements nameList = document.select(".info_name .gd_name");
                // 책가격: .info_price > .yes_b > .txt_num
                Elements priceList = document.select(".info_price .txt_num .yes_b");
                // 책사진: .img_bdr > .lazy
                Elements imageList = document.select(" .lazy");
                // 4. 반복문을 이용하여 여러 개 요소/마크업들을 도서별 MAP 구성하여 LIST 저장
                for(int index=0; index< nameList.size();index++){
                    String name = nameList.get(index).text(); // index번째 요소의 책이름(텍스트) 반환
                    String price = priceList.get(index).text();
                        // text( ): 마크업 사이 텍스트 반환.
                        // attr( 속성명 ): 해당 속성명의 속성값
                    String image = imageList.get(index).attr("data-original");
                    // 5. DTO/MAP 구성
                    Map<String, Object> map = new HashMap<>();
                    map.put("name", name);       map.put("price", price);        map.put("image", image);
                    // 6.
                    list.add(map);
                }
            }
        }catch (Exception e){System.out.println(e);}
        return list;
    }

    // 3.
    public Map<String,Object> test3(){
        // 1. 크롬 설치
        WebDriverManager.chromedriver().setup();
        // 2. 웹 크롤링할 주소
        String url = "https://weather.daum.net/";
        // 3. 크롬 드라이버 객체 생성
            // * 드라이버 옵션
        ChromeOptions options = new ChromeOptions();
        options.addArguments( "--headless=new" , "--disable-gpu" );
            // 크롬은 백그라운드 실행을 지원한다. "gpu를 백그라운드에서만 실행한다."
        WebDriver webDriver = new ChromeDriver( options );
        // 4. 크롬 드라이버 객체에 크롤링할 주소 넣기
        webDriver.get(url);
        // 5. 해당 페이지는 동적( 데이터 표현하는데 부분적 시간필요 ) 페이지
            // new WebDriverWait( 현재크롬객체 , Duration.ofXXX );
            // 로봇인 거 안 걸리게 1초 정도 대기 ㅋㅋ...
        WebDriverWait wait = new WebDriverWait( webDriver , Duration.ofSeconds( 1 ) ); // 1초 기다림
        // 6. 크롤링할 선택자
            // WebElement 변수명 = wait.until( ExpectedConditions.presenceOfElementLocated( By.cssSelector("선택자") ) )
            // 1. 온도: info_weather -> num_deg
        WebElement temp = wait.until(
                ExpectedConditions.presenceOfElementLocated( By.cssSelector(".info_weather .num_deg") )
        );
        System.out.println( temp.getText() ); // 크롤링된 요소 마크업 확인 용도
            // 2. 체감온도: ico_weather
        WebElement temp2 = wait.until(
                ExpectedConditions.presenceOfElementLocated( By.cssSelector(".tooltip_icon .ico_airstat1"))
        );
        System.out.println( temp2.getText() ); // 크롤링된 요소 마크업 확인 용도
        // 7. 가져온 정보들을 dto/map으로 구성
        Map<String, Object> map = new HashMap<>();
        map.put("온도", temp.getText());        map.put("초미세먼지", temp2.getText());
        // 8. 안전하게 드라이며 객체 종료
        webDriver.quit(); // 메모리 효율성 때문에 직접 종료하기
        // 9. map 반환
        return map;
    }

    // 4. CGV 특정 영화 관람평 크롤링 (+무한스크롤)
        // 트랜드 분석은 sns 크롤링을 통해 하게 된다.
    public List<String> test4(){
        // 1. 크롬 설치
        WebDriverManager.chromedriver().setup();
        // 2. 웹 크롤링할 주소
        String url = "https://cgv.co.kr/cnm/cgvChart/movieChart/30000927";
            // String url = "https://cgv.co.kr/cnm/cgvChart/movieChart/"+num; 반복문 돌려서 이렇게 하면 영화 많이 가져올 수 있
        // 3. 크롬 객체
        WebDriver webDriver = new ChromeDriver();
        // 4. 크롬 객체 내 크롤링 url 대입 하고 시행
        webDriver.get( url );
        // 5. 백그라운드 실행 (드라이버옵션) -> 홈페이지 문제로 크롤링 진행 안 돼서 뺌

        // **** 자바에서 JS를 제어하여 자동으로 스크롤링 내리는 작업 ****
            // 해당하는 크롬 객체에서 JS객체 꺼내기
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
            // : .executeScript( "JS문법" );
        js.executeScript("window.scrollTo( 100 , document.body.scrollHeight )");
                // window.scrollTo( 100 , document.body.scrollHeight );
                // document.body.scrollHeight: 현재 화면에서 스크롤 전체 길이=높이=3000px , 상단꼭지점=0 , 하단꼭지점=300. (우리는 100에 놓음)
                // scrollTo( 이동할위치 , 전체길이 )
        // 1초 대기
        try{ Thread.sleep( 1000 ); }catch ( Exception e ){System.out.println(e);}

        // *** 크롤링할 선택자로 요소 크롤링 *** (.reveiwCard_txt__RrTgu)
        List<String> list = new ArrayList<>(); // 내용 넣을 리스트 생성
        for( int page=1 ; page<=10; page++){
            // 특정 반복문이 시작되었을 때. 현재 리뷰 개수
            int startCount = list.size();
            // 1개 요소 : WebElement  vs.  여러 개 요소 : List<WebElement>
            List<WebElement> elements = webDriver.findElements( By.cssSelector(".reveiwCard_txt__RrTgu") );
                // sait.until  vs.  webDriver.find
                // -> wait(기다림) 없을 경우엔 Driver에서 바로 find. 있으면 wait.util 해주기
            System.out.println("elements = " + elements); //확인용
            for( WebElement element : elements ){
                System.out.println(element); // 확인용
                // 만약에 리스트에 없는 리뷰이면 추가, 아니면 추가 안 한다.
                    // 가져온 값이랑 중복되는지 확인하는 거~~
                String review = element.getText();
                    // .contains( 찾을값 ): 만약에 찾을 값이 존재하면 true, 아니면 false
                if( list.contains(review) ){continue;}
                else{ list.add( review ); }
            }
            // 특정 반복문이 한 번 종료 되었을 때
            int endCount = list.size();
            // 리뷰의 시작 개수와 끝 개수가 같다면 크롤링 중지
            if( startCount == endCount ){break;}
                // 같지 않다면 크롤링 계속...
            // ============= 자바에서 JS 사용 : 스크롤을 내리는 작업 =================== //
            // document.body( 화면 ) 에서 최하단으로 스크롤 이동
            js.executeScript("window.scrollTo( 100 , document.body.scrollHeight ); ");
            try { Thread.sleep( 1000 ); } catch (Exception e) {}  // 1초 대기
        }
        return list;
    }

}

/*
### 크롤링
- 웹크롤링
   : 웹(테이지의) HTML 정보/자료 수집 과정
- 웹 페이지 마다 크롤링 허용 여부를 확인해야 함.
   : URL뒤에 `/robots.txt`
   - 잡코리아 예시) https://www.jobkorea.co.kr/robots.txt
- 정적 페이지: Jsoup 라이브러리
   - Jsoup 라이브러리 추가: implementation 'org.jsoup:jsoup:1.21.2'
- 동적 페이지: Selenium 라이브러리 (* 파이썬과 동일하다 *)
   - 스프링이 지원하는 공식 라이브러리: https://start.spring.io/
   - 그 외 라이브러리: https://mvnrepository.com/
    - https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java/4.41.0
    : implementation 'org.seleniumhq.selenium:selenium-java:4.35.0'

*/

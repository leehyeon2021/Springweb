package springweb.board.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springweb.board.dto.BoardDto;
import springweb.board.service.BoardService;
import springweb.member.service.JWTService;

@RestController @RequiredArgsConstructor
@RequestMapping("/api/board")
@CrossOrigin( value = "http://localhost:5173" , exposedHeaders = "Authorization",
                allowCredentials = "true"
)
public class BoardController {
    private final BoardService boardService;
    private final JWTService jwtService;

    // 1. 회원제 글등록 + 세션 정보(회원)
    @PostMapping("/write")
    public ResponseEntity<?> write(@RequestBody BoardDto boardDto , HttpSession session ){
        // 1. 세션 내 로그인 정보 확인
        Object obj = session.getAttribute("loginMid");
        // 2. 비로그인이면 글쓰기 실패
        if(obj==null){return ResponseEntity.ok(false);}
        // 3. 로그인 중이면
        String loginMid = (String)obj;
        // 4. 서비스에게 입력받은 값과 세션에 저장된 값을 전달한다.
        return ResponseEntity.ok(boardService.write(boardDto, loginMid));
    }

    // 1-2. 회원제 글등록 + 토큰 정보
    @PostMapping("/write2")
    public ResponseEntity<?> write2(@RequestBody BoardDto boardDto , @RequestHeader("Authorization") String token ){
        // 1. 매개변수로 jwt 토큰을 받는다.
        // 2. 문자열.startsWith( "시작문자" ) : 문자열 내 시작문자가 존재하면 true
        if( token == null || !token.startsWith("Bearer") ){
            return ResponseEntity.ok(false); // 비로그인이라서 글쓰기 실패
        }
        // 3. 토큰만 추출
        token = token.replace("Bearer " , "" );
        // 3. 토큰에서 클레임(값) 꺼내기
        String loginMid = jwtService.getClaim( token );
        if( loginMid == null ){return ResponseEntity.ok( false );}
        // 4. 서비스에게 입력받은 값과 세션에 저장된 값을 전달한다.
        boolean result = boardService.write( boardDto , loginMid );
        return ResponseEntity.ok( result );
    }

    // 1-3. 회원제 글등록 + 토큰 정보 + 첨부파일( content-type: multipart/form-data 변경 )
    @PostMapping("/write3")
    public ResponseEntity<?> write3( BoardDto boardDto , @RequestHeader("Authorization") String token ){
        // 1-2와 같음.
        if( token == null || !token.startsWith("Bearer") ){return ResponseEntity.ok(false);}
        token = token.replace("Bearer " , "" );
        String loginMid = jwtService.getClaim( token );
        if( loginMid == null ){return ResponseEntity.ok( false );}
        boolean result = boardService.write( boardDto , loginMid );
        return ResponseEntity.ok( result );
    }


    // 1-4. 회원제 글 등록 + 토큰 정보 + 첨부 파일 + 쿠키
    @PostMapping("/write4")
    public ResponseEntity<?> write4(
                                        BoardDto boardDto
                                        , @CookieValue( value = "token" , required = false) String token ){
        // 1. @CookieValue("token" , required = false)
        if( token == null ){return ResponseEntity.ok(false);}

        token = token.replace("Bearer " , "" );
        String loginMid = jwtService.getClaim( token );
        if( loginMid == null ){return ResponseEntity.ok( false );}
        boolean result = boardService.write( boardDto , loginMid );
        return ResponseEntity.ok( result );
    }

    // 2. 전체조회
    @GetMapping("/list")
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok( boardService.findAll() );
    }

    // 3. 개별조회
    @GetMapping("/view")
    public ResponseEntity<?> findOne( @RequestParam Long bno ){
        return ResponseEntity.ok( boardService.findOne( bno ) );
    }
}

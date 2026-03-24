package springweb.board.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springweb.board.dto.BoardDto;
import springweb.board.service.BoardService;

@RestController @RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {
    private final BoardService boardService;

    // 1. 회원제 글등록 + 세션 정보(회원)
    @PostMapping("/post")
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
}

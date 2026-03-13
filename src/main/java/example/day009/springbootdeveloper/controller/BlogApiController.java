package example.day009.springbootdeveloper.controller;

import example.day009.springbootdeveloper.domain.Article;
import example.day009.springbootdeveloper.dto.AddArticleRequest;
import example.day009.springbootdeveloper.dto.ArticleResponse;
import example.day009.springbootdeveloper.dto.UpdateArticleRequest;
import example.day009.springbootdeveloper.repository.BlogRepository;
import example.day009.springbootdeveloper.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor // @Autowired 생략 가능. 근데 final 써야 함.
@RestController // HTTP Response Body에 객체 데이터를 JSON 형식으로 반환하는 컨트롤러
public class BlogApiController {

    private final BlogService blogService;

    // HTTP 메서드가 POST일 때 전달받은 URL과 동일하면 메서드로 매핑
    @PostMapping("/api/articles")
    // @RequestBody로 요청 본문 값 매핑
    // ResponseEntity<반환타입> : HTTP 응답 객체를 수정/커스텀 가능하다. 응답정보를 수정하겠다. **응답 객체를 포장** --> return할 때
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request){
        Article savedArticle = blogService.save(request);
        // 요청한 자원이 성공적으로 생성되었으며 저장된 블로그 글 정보를 응답 객체에 담아 전송
        return ResponseEntity.status(HttpStatus.CREATED) // --> 180쪽에 응답코드 6개 나와있음. 암기하기.
                .body(savedArticle);
    }

    // 블로그 조회 메서드
    @GetMapping("/api/articles")
    public ResponseEntity< List<ArticleResponse> > findAllArticles(){
//        List<ArticleResponse> articles = blogService.findAll()
//                .stream()
//                .map(ArticleResponse::new)
//                .toList();
        // 교재는 stram.map방식. 대신 forEach 방식으로 진행
        // 1. 서비스로 부터 모든 엔티티 반환
        List<Article> articleList = blogService.findAll();
        // 2. 모든 엔티티를 forEach 활용하여 하나씩 반복변환
        List<ArticleResponse> articleResponses = new ArrayList<>();
        articleList.forEach(entity -> {
            ArticleResponse articleResponse = new ArticleResponse(entity);
            articleResponses.add(articleResponse);
        });
//        return ResponseEntity.ok().body(articles);
        return ResponseEntity.status(200).body(articleResponses);
    }

    // 블로그 조회 메서드 - 하나
    @GetMapping("/api/articles/{id}")
    // URL 경로에서 값 추출
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable("id") long id){
        Article article = blogService.findById(id);
        return ResponseEntity.ok()
                .body(new ArticleResponse(article));
    }

    // 삭제
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable("id") long id){
        blogService.delete(id);
        return ResponseEntity.ok()
                .build();
    }

    // 수정
    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable("id") long id, @RequestBody UpdateArticleRequest request){
        Article updateArticle = blogService.update(id, request);
        return ResponseEntity.ok().body(updateArticle);
    }
}

package example.day009.springbootdeveloper.service;

import example.day009.springbootdeveloper.domain.Article;
import example.day009.springbootdeveloper.dto.AddArticleRequest;
import example.day009.springbootdeveloper.dto.UpdateArticleRequest;
import example.day009.springbootdeveloper.repository.BlogRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor // final이 붙거나 @NotNull이 붙은 필드의 생성자 추가 --> 즉: AutoWired 안 써도 됨(단, final 써야 함).
@Service // 빈으로 등록
public class BlogService {

    // @Autowired private BlogRepository blogRepository; // 방법1
    private final BlogRepository blogRepository; // 방법2

    // 블로그 글 추가 메서드
    public Article save(AddArticleRequest request){
        return blogRepository.save(request.toEntity());
    }

    // 블로그 조회 메서드
    public List<Article> findAll(){
        return blogRepository.findAll();
    }

    // 블로그 조회 메서드 - 하나
    public Article findById(long id){
//        return blogRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("not found: "+id));
        // 1. 매개변수로 받은 id로 엔티티 조회
        Optional<Article> optionalArticle = blogRepository.findById(id);
        // 2. 엔티티가 존재하면
        if(optionalArticle.isPresent()){
            // 3. 가져오기
            return optionalArticle.get();
        }
        // 4. 반환
        return null;
    }

    // 삭제
    public void delete(long id){
        blogRepository.deleteById(id);
    }

    // 수정
    @Transactional
    public Article update(long id , UpdateArticleRequest request){
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: "+id));
        article.update(request.getTitle(), request.getContent());
        return article;
    }

}

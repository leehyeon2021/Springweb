package example.day009.springbootdeveloper.dto;

import example.day009.springbootdeveloper.domain.Article;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor @Getter
public class ArticleViewResponse {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    public ArticleViewResponse(Article article){
        this.id=article.getId();
        this.content=article.getContent();
        this.title=article.getTitle();
        this.createdAt=article.getCreateAt();
    }
}

package example.day009.springbootdeveloper.dto;

import example.day009.springbootdeveloper.domain.Article;
import lombok.Getter;

@Getter
public class ArticleListViewResponse {
    private final Long id;
    private final String title;
    private final String content;

    public ArticleListViewResponse(Article article){
        this.id=article.getId();
        this.content= article.getContent();
        this.title= article.getTitle();
    }
}

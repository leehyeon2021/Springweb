package example.day006.practice6;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor @Data @Builder
public class MovieDto {
    private Integer movieid;
    private String title;
    private String director;
    private String releasedate;
    private Integer rating;
    private String createDate;
    private String updateDate;

    // Dto --> Entity
    public MovieEntity toEntity(){
        return MovieEntity.builder()
                .movieid(this.movieid).title(this.title).director(this.director).releasedate(this.releasedate).rating(this.rating)
                .build();
    }
}

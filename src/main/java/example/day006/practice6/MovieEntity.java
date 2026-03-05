package example.day006.practice6;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Table(name="movie")
@AllArgsConstructor @NoArgsConstructor @Data @Builder
public class MovieEntity extends BaseTime{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "영화번호" )
    private Integer movieid;

    @Column( name = "영화제목" , length = 100 )
    private String title;

    @Column( name = "감독" , length = 100 )
    private String director;

    @Column( name = "개봉일" , length = 15 )
    private String releasedate;

    @Column( name = "평점" )
    private Integer rating;

    // Entity --> Dto
    public MovieDto toDto(){
        return MovieDto.builder()
                .movieid(this.movieid).title(this.title).director(this.director).releasedate(this.releasedate).rating(this.rating).createDate(getCreateDate().toString()).updateDate(getUpdateDate().toString())
                       .build();
    }

}

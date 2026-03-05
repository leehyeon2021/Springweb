package example.day006.practice6;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service @Transactional
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    // 영화 등록
    public boolean mSave( MovieDto movieDto ){
        // 1. dto -> entity
        // 2. JPA save 이용하여 엔티티 insert
        // 3. save 결과를
        return movieRepository.save(movieDto.toEntity()).getMovieid() >= 1;
    }

    // 영화 전체 조회: 모든 영화 목록을 조회
    public List<MovieDto> mFindAll(){
        List<MovieEntity> movieEntityList = movieRepository.findAll();
        List<MovieDto> movieDtoList = new ArrayList<>();
        movieEntityList.forEach( movieEntity -> {
            MovieDto movieDto = new MovieDto();
            movieDto.setMovieid(movieEntity.getMovieid());            movieDto.setTitle(movieEntity.getTitle());
            movieDto.setDirector(movieEntity.getDirector());            movieDto.setDirector(movieEntity.getDirector());
            movieDto.setRating(movieEntity.getRating());            movieDto.setReleasedate(movieEntity.getReleasedate());
            movieDtoList.add(movieDto);
        });
        return movieDtoList;
    }


    // 영화 개별 조회: 영화번호(movieid)를 기준으로 특정 영화 상세 정보 조회
    public List<MovieDto> mFindOne(int movieid){
        Optional<MovieEntity> movieEntityList = movieRepository.findById(movieid);
        List<MovieDto> movieDtoList = new ArrayList<>();
        if(movieEntityList.isPresent()){
            MovieEntity list = movieEntityList.get();
            movieDtoList.add(MovieDto.builder()
                    .movieid(list.getMovieid()).title(list.getTitle()).director(list.getDirector()).releasedate(list.getReleasedate()).rating(list.getRating())
                    .build());
        }else {System.out.println("개별조회 실패 (Service)");}
        return movieDtoList;
    }

    // 특정 영화 수정: 영화번호(movieid)를 기준으로 해당 영화 정보 수정. @Transactional의 역할을 서술
    public boolean mUpdate(MovieDto movieDto){
        return false;
    }

    // 특정 영화의 삭제: 영화번호(movieid)를 기준으로 해당 영화 삭제
    public boolean mDelete(int movieid){
        return false;
    }

}

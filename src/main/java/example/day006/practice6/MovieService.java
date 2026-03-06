package example.day006.practice6;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional // @Transactional: 수정할 땐 setter가 여러 개 사용된다 - 여러 개를 단일 실행으로 처리해준다.
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
            MovieDto movieDto = movieEntity.toDto();
            movieDtoList.add(movieDto);
        });
        return movieDtoList;
    }


    // 영화 개별 조회: 영화번호(movieid)를 기준으로 특정 영화 상세 정보 조회
    public MovieDto mFindOne(int movieid){
        Optional<MovieEntity> optional = movieRepository.findById(movieid);
        if(optional.isPresent()) {
            MovieEntity movieEntity = optional.get();
            MovieDto movieDto = movieEntity.toDto();
            return movieDto;
        }
        return null;
    }

    // 특정 영화 수정: 영화번호(movieid)를 기준으로 해당 영화 정보 수정.
    public boolean mUpdate(MovieDto movieDto){
        // 1. 수정할 entity의 pk번호 확인
        int updatePk = movieDto.getMovieid();
        // 2. 수정할 entity 찾기 (영속성 때문에 Optional로 포장해서 반환)
        Optional<MovieEntity> optional = movieRepository.findById(updatePk);
        // 3. 만약에 찾은 엔티티가 존재하면 꺼내기
        if(optional.isPresent()){
            MovieEntity updateEntity = optional.get();
            updateEntity.setTitle(movieDto.getTitle());
            updateEntity.setDirector(movieDto.getDirector());
            updateEntity.setReleasedate(movieDto.getReleasedate());
            updateEntity.setRating(movieDto.getRating());
            return true;
        }
        return false;
    }

    // 특정 영화의 삭제: 영화번호(movieid)를 기준으로 해당 영화 삭제
    public boolean mDelete(int movieid){
        try{
            movieRepository.deleteById( movieid );
            return true;
        }catch (Exception e){System.out.println(e);return false;}
    }

}

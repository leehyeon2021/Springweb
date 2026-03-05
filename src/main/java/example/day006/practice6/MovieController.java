package example.day006.practice6;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/movie")
public class MovieController {
    @Autowired
    private MovieService movieService;

    // 영화 등록
    @PostMapping
    public boolean mSave(@RequestBody MovieDto movieDto){
        return movieService.mSave( movieDto );
    }

    // 영화 전체 조회: 모든 영화 목록을 조회
    public List<MovieDto> mFindAll(){

    }


    // 영화 개별 조회: 영화번호(movieid)를 기준으로 특정 영화 상세 정보 조회
    public List<MovieDto> mFindOne(int movieid){

    }

    // 특정 영화 수정: 영화번호(movieid)를 기준으로 해당 영화 정보 수정. @Transactional의 역할을 서술
    public boolean mUpdate(MovieDto movieDto){

    }

    // 특정 영화의 삭제: 영화번호(movieid)를 기준으로 해당 영화 삭제
    public boolean mDelete(int movieid){

    }

}

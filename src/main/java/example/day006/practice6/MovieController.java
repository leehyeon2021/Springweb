package example.day006.practice6;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping
    public List<MovieDto> mFindAll(){return movieService.mFindAll();}

    // 영화 개별 조회: 영화번호(movieid)를 기준으로 특정 영화 상세 정보 조회
    @GetMapping("/one")
    public MovieDto mFindOne(@RequestParam int movieid){return movieService.mFindOne(movieid);}

    // 특정 영화 수정: 영화번호(movieid)를 기준으로 해당 영화 정보 수정. @Transactional의 역할을 서술
    @PutMapping
    public boolean mUpdate(@RequestBody MovieDto movieDto){
        return movieService.mUpdate(movieDto);
    }

    // 특정 영화의 삭제: 영화번호(movieid)를 기준으로 해당 영화 삭제
    @DeleteMapping
    public boolean mDelete(@RequestParam int movieid){
        return movieService.mDelete(movieid);
    }

}

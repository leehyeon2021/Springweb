package practice.practice1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import practice.practice1.dto.TaskDto;
import practice.practice1.service.TaskService;

@RestController
@CrossOrigin(origins = "http://localhost:5173") // 서로 다른 port(프로그램식별번호) 간의 통신 허용
// SOP 정책으로 서로 다른 도메인은 통신이 불가능하다. HTTP 보안 정책
// CORS : 교차 출처 리소스 공유 , 즉] 서로 다른 도메인(8080스프링,5173리액트) 통신 공유 허용
@RequiredArgsConstructor // final 멤버변수 생성자 자동(  @Autowired )
@RequestMapping( "/api/task") // 리액트 경로 = /task vs 스프링 경로 = /api/task 중복될 수 있으므로 구분한다.
public class TaskController {
    private final TaskService taskService;

    // 등록
    @PostMapping("")
    public ResponseEntity<?> taskPost(@RequestBody TaskDto taskDto){
        return ResponseEntity.ok(taskService.taskPost(taskDto));
    }

    // 목록 조회
    @GetMapping("/list")
    public ResponseEntity<?> taskGetList(){
        return ResponseEntity.ok(taskService.taskGetList());
    }

    // 상세 조회 param
    @GetMapping("/detail")
    public ResponseEntity<?> taskGetDetail(@RequestParam int id){
        return ResponseEntity.ok(taskService.taskGetDetail(id));
    }

    // 수정 param
    @PutMapping("")
    public ResponseEntity<?> taskUpdate(@RequestParam TaskDto taskDto){
        return ResponseEntity.ok(taskService.taskUpdate(taskDto));
    }

    // 삭제 param
    @DeleteMapping("")
    public ResponseEntity<?> taskDelete(@RequestParam int id){
        return ResponseEntity.ok(taskService.taskDelete(id));
    }

}

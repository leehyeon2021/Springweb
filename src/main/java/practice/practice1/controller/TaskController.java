package practice.practice1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import practice.practice1.dto.TaskDto;
import practice.practice1.service.TaskService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/task")
@CrossOrigin(origins = "http://localhost:5173")
public class TaskController {
    private TaskService taskService;

    // 등록
    @PostMapping("")
    public ResponseEntity<?> taskPost(@RequestBody TaskDto taskDto){
        return ResponseEntity.ok(taskService.taskPost(taskDto));
    }

//    // 목록 조회
//    @GetMapping("/list")
//    public ResponseEntity<?> taskGetList(){
//
//    }
//
//    // 상세 조회 param
//    @GetMapping("/detail")
//    public ResponseEntity<?> taskGetDetail(){
//
//    }
//
//    // 수정 param
//    @PutMapping("")
//    public ResponseEntity<?> taskUpdate(){
//
//    }
//
//    // 삭제 param
//    @DeleteMapping("")
//    public ResponseEntity<?> taskDelete(){
//
//    }

}

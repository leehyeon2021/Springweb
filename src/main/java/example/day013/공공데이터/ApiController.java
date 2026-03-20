package example.day013.공공데이터;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequiredArgsConstructor @RequestMapping("/api")
public class ApiController {
    private final ApiService apiService;

    // 1. JSON
    @GetMapping("/test1")
    public ResponseEntity<?> test1(){
        return ResponseEntity.ok(apiService.test1());
    }

    // 2. XML
    @GetMapping("/test2")
    public ResponseEntity<?> test2(){
        return ResponseEntity.ok(apiService.test2());
    }

}

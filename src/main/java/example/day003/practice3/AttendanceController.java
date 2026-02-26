package example.day003.practice3;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {
    // 1. 출석 등록
    @PostMapping
    public boolean POST(@RequestBody AttendanceDto attendanceDto){

    }
}

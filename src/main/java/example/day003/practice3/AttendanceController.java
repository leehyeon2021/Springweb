package example.day003.practice3;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    List<AttendanceDto> list = new ArrayList<>();

    // 1. 출석 등록
    @PostMapping
    public boolean POST(@RequestBody AttendanceDto attendanceDto){
        try{
            list.add(attendanceDto);
            return true
        }catch (Exception e){
            return false;
        }
    }

    // 2. 출석 전체조회
    @GetMapping
    public List<AttendanceDto> GET1(){
        return list;
    }

    // 3. 출석 개별조회
    @GetMapping("/detail")
    public AttendanceDto GET2( @RequestParam int ano ){

    }


}

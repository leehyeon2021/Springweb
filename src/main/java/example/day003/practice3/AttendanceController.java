package example.day003.practice3;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    // 임의의 값 저장하려고 만듦
    List<AttendanceDto> list = new ArrayList<>();

    // 1. 출석 등록
    @PostMapping
    public boolean POST(@RequestBody AttendanceDto attendanceDto){
        try{
            list.add(attendanceDto);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    // 2. 출석 전체조회
    @GetMapping
    public List<AttendanceDto> GET1(){
        return list;
    }
    // +) 2. Build 써보기
    @GetMapping("/builder")
    public List<AttendanceDto> GET(){
        List<AttendanceDto> list = new ArrayList<>();
        list.add( new AttendanceDto(1 , "동그라미", "2026-02-26" , "출석"));
        list.add( AttendanceDto.builder().status("출석").studentName("세모").ano(2).date("2026-02-26").build());
        return list;
    }

    // 3. 출석 개별조회
    @GetMapping("/detail")
    public AttendanceDto GET2( @RequestParam int ano ){
        for( AttendanceDto attendance : list ){
            if(attendance.getAno() == ano){
                return attendance;
            }
        }
        return null;
    }

    // 4. 출석 삭제
    @DeleteMapping
    public boolean DELETE( @RequestParam int ano ){
        if(list.removeIf(attendanceDto -> (attendanceDto.getAno() == ano) ) ){
            return true;
        }
        return false;
    }

    // 5. 출석 수정
    @PutMapping
    public boolean PUT( @RequestBody AttendanceDto attendanceDto ){
        for(int i=0;i<list.size();i++){
            if(list.get(i).getAno() == attendanceDto.getAno()){
                list.set(i, attendanceDto);
                return true;
            }
        }
        return false;
    }

}

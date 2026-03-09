package example.day007.practice7.controller;

import example.day007.practice7.dto.EnrollDto;
import example.day007.practice7.service.EnrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enroll")
public class EnrollController {
    @Autowired
    private EnrollService es;

    // 등록기능3
    @PostMapping
    public boolean eSave(@RequestBody EnrollDto enrollDto){
        return es.eSave(enrollDto);
    }

    // 조회기능
    @GetMapping
    public List<EnrollDto> eFindAll(){
        return es.eFindAll();
    }
}

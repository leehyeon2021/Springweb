package example.day007.practice7.controller;

import example.day007.practice7.dto.EnrollDto;
import example.day007.practice7.service.EnrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    // 교수님 - DTO 없이 Map 사용하심
    @PostMapping("/add")
    public boolean addE(@RequestBody Map<String , Object> map){
        return es.addE(map);
    }
    // 조회 - 개별
    @GetMapping("/get")
    public Map<String,Object>get(@RequestParam int enrollid){
        return es.get(enrollid);
    }
}

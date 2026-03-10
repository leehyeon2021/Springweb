package example.day007.practice7.controller;

import example.day007.practice7.dto.CourseDto;
import example.day007.practice7.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService cs;

    // 등록기능1
    @PostMapping
    public boolean cSave(@RequestBody CourseDto courseDto){
        return cs.cSave(courseDto);
    }

    // 교수님 - DTO 없이 Map 사용하심
    @PostMapping("/add")
    public boolean addC(@RequestBody Map<String , Object> map){
        return cs.addC(map);
    }
}

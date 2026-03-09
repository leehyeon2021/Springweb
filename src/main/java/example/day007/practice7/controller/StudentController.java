package example.day007.practice7.controller;

import example.day007.practice7.dto.StudentDto;
import example.day007.practice7.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService ss;

    // 등록기능2
    @PostMapping
    public boolean sSave(@RequestBody StudentDto studentDto){
        return ss.sSave(studentDto);
    }
}

package example.day007.practice7.controller;

import example.day007.practice7.service.EnrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enroll")
public class EnrollController {
    @Autowired
    private EnrollService es;

    // 등록기능3
}

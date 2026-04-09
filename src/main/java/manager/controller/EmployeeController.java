package manager.controller;

import lombok.RequiredArgsConstructor;
import manager.service.EmployeeService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;


}

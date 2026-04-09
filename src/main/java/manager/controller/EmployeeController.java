package manager.controller;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import manager.dto.EmployeeDto;
import manager.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employee")
@CrossOrigin( value = "http://localhost:5173" )
public class EmployeeController {
    private final EmployeeService employeeService;

    // 사원 등록
    @PostMapping("/add")
    public ResponseEntity<?> addEmp(EmployeeDto employeeDto){
        return ResponseEntity.ok(employeeService.addEmp(employeeDto));
    }

    // 사원 조회
    @GetMapping("/findAll")
    public ResponseEntity<?> findAllEmp(){
        return ResponseEntity.ok(employeeService.findAllEmp());
    }

    // 사원 수정
    @Transactional
    @PutMapping("/update")
    public ResponseEntity<?> updateEmp(@RequestBody EmployeeDto employeeDto){
        return ResponseEntity.ok(employeeService.updateEmp(employeeDto));
    }

    // 사원 삭제
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteEmp(@RequestParam Integer eId){
        return ResponseEntity.ok(employeeService.deleteEmp(eId));
    }

}

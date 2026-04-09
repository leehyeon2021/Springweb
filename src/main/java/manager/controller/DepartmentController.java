package manager.controller;

import lombok.RequiredArgsConstructor;
import manager.dto.DepartmentDto;
import manager.service.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/department")
@CrossOrigin( value = "http://localhost:5173" )
public class DepartmentController {
    private final DepartmentService departmentService;

    // 부서 등록
    @PostMapping("/add")
    private ResponseEntity<?> addDep(@RequestBody DepartmentDto departmentDto){
        return ResponseEntity.ok(departmentService.addDep(departmentDto));
    }

    // 부서 조회
    @GetMapping("/findAll")
    public ResponseEntity<?> findAllDep(){
        return ResponseEntity.ok(departmentService.findAllDep());
    }

    // 부서 수정
    @PutMapping("/update")
    public ResponseEntity<?> updateDep(@RequestBody DepartmentDto departmentDto){
        return ResponseEntity.ok(departmentService.updateDep(departmentDto));
    }

    // 부서 삭제
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteDep(@RequestParam Integer dId){
        return ResponseEntity.ok(departmentService.deleteDep(dId));
    }


}

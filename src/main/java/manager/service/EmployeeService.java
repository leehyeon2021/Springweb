package manager.service;

import lombok.RequiredArgsConstructor;
import manager.dto.EmployeeDto;
import manager.entity.DepartmentEntity;
import manager.entity.EmployeeEntity;
import manager.repository.DepartmentRepository;
import manager.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final FileService fileService;

    private final String defaultImg = "https://placehold.co/100";

    // 사원 등록
    public boolean addEmp(EmployeeDto employeeDto){

        EmployeeEntity saveEntity = employeeDto.toEntity();

        // fk
        Optional<DepartmentEntity> entityOptional = departmentRepository.findById(employeeDto.getDId());
        System.out.println(entityOptional);
        if(!entityOptional.isPresent()){
            System.out.println("[존재하지 않는 부서] "+employeeDto.getDId());
            return false;
        }
        saveEntity.setDepartmentEntity(entityOptional.get());

        String fileName = fileService.upload(employeeDto.getUploadFile());
        System.out.println(fileName);

        if( fileName != null ){
            saveEntity.setEFile( fileName );
        }else {
            saveEntity.setEFile(defaultImg);
        }

        try {
            employeeRepository.save(saveEntity);
            return true;
        }catch (Exception e) {
            System.out.println("예외 발생: "+e);
            return false;
        }
    }

    // 사원 조회
    public List<EmployeeDto> findAllEmp(){
        return employeeRepository.findAll().stream()
                .map(EmployeeEntity::toDto)
                .collect(Collectors.toList());
    }

    // 사원 수정
    public boolean updateEmp(EmployeeDto employeeDto){
        return employeeRepository.findById(employeeDto.getEId())
                .map(entity -> {
                    entity.setEName(employeeDto.getEName());
                    entity.setERank(employeeDto.getERank());
                    return true;
                })
                .orElse(false);
    }

    // 사원 삭제
    public boolean deleteEmp(Integer eId){
        if(employeeRepository.existsById(eId)){
            employeeRepository.deleteById(eId);
            return true;
        }
        return false;
    }
}

package manager.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import manager.dto.DepartmentDto;
import manager.entity.DepartmentEntity;
import manager.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    // 부서 등록
    public boolean addDep(DepartmentDto departmentDto){
        return departmentRepository.save(departmentDto.toEntity()).getDId()>=1;
    }

    // 부서 조회
    public List<DepartmentDto> findAllDep(){
        return departmentRepository.findAll().stream()
                .map(DepartmentEntity :: toDto)
                .collect(Collectors.toList());
    }

    // 부서 수정
    @Transactional
    public boolean updateDep(DepartmentDto departmentDto){
        return departmentRepository.findById(departmentDto.getDId())
                .map(entity -> {
                    entity.setDName(departmentDto.getDName());
                    return true;
                })
                .orElse(false);
    }

    // 부서 삭제
    public boolean deleteDep(Integer dId){
        if(departmentRepository.existsById(dId)){
            departmentRepository.deleteById(dId);
            return true;
        }
        return false;
    }

}

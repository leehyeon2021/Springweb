package example.day007.practice7.service;

import example.day007.practice7.dto.EnrollDto;
import example.day007.practice7.entity.EnrollEntity;
import example.day007.practice7.repository.EnrollRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service @Transactional
public class EnrollService {
    @Autowired private EnrollRepository er;

    // 등록기능3
    public boolean eSave(EnrollDto enrollDto){
        return er.save(enrollDto.toEntity()).getEnrollId()>=1;
    }

    // 조회기능
    public List<EnrollDto> eFindAll(){
        List<EnrollEntity> enrollEntityList = er.findAll();
        List<EnrollDto> enrollDtoList = new ArrayList<>();
        enrollEntityList.forEach(entity ->{
            EnrollDto enrollDto = entity.toDto();
            enrollDtoList.add(enrollDto);
        });
        return enrollDtoList;
    }
}

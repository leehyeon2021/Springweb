package practice.practice1.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import practice.practice1.dto.TaskDto;
import practice.practice1.entity.TaskEntity;
import practice.practice1.repository.TaskRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    // 등록
    public boolean taskPost(TaskDto taskDto){
        return taskRepository.save(taskDto.toEntity()).getId()>=1;
    }

    // 목록 조회
    public List<TaskDto> taskGetList(){
        return taskRepository.findAll().stream()
                .map(TaskEntity::toDto)
                .collect(Collectors.toList());
    }

    // 상세 조회
    public TaskDto taskGetDetail(int id){
        return taskRepository.findById(id)
                .map(TaskEntity::toDto)
                .orElse(null);
    }

    // 수정
    @Transactional
    public boolean taskUpdate(TaskDto taskDto){
        return taskRepository.findById(taskDto.getId())
                .map(taskEntity -> {
                    taskEntity.setTitle(taskDto.getTitle());
                    taskEntity.setContent(taskDto.getContent());
                    taskEntity.setRequester(taskDto.getRequester());
                    taskEntity.setStatus(taskDto.getStatus());
                    return true;
                })
                .orElse(false);
    }

    // 삭제
    public boolean taskDelete(int id){
        if(taskRepository.existsById(id)){
            taskRepository.deleteById(id);
            return true;
        }
        return false;
    }

}

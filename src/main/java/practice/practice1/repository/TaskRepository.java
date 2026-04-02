package practice.practice1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import practice.practice1.entity.TaskEntity;

public interface TaskRepository extends JpaRepository<TaskEntity, Integer> {}

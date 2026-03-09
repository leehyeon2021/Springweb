package example.day007.practice7.repository;

import example.day007.practice7.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentEntity, Integer>{}

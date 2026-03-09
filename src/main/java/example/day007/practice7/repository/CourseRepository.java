package example.day007.practice7.repository;

import example.day007.practice7.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<CourseEntity , Integer>{}

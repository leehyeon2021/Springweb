package example.day007.practice7.repository;

import example.day007.practice7.entity.EnrollEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollRepository extends JpaRepository<EnrollEntity, Integer>{}

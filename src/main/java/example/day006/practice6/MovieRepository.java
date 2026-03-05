package example.day006.practice6;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository< MovieEntity , Integer >{}

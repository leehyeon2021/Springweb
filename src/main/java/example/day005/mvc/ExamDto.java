package example.day005.mvc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExamDto {
    private Integer eno;
    private String ename;
}
// DTO: 이동 객체!!
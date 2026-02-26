package example.day003.practice3;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AttendanceDto {
    private Integer ano; // 래퍼클래스: 만약 ano를 입력 받지 못했다면 null 값을 받아줌.
    private String studentName;
    private String date;
    private String status;

}

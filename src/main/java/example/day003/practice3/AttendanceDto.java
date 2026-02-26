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
    private int ano;
    private String studentName;
    private String date;
    private String status;

}

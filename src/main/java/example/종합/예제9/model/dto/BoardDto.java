package example.종합.예제9.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data // @Getter + @Setter + @ToString + @RequiredArgsConstructor(final 매개변수 생성자 자동 생성)
public class BoardDto {
    private Integer bno; // int -> Integer 사용하여 null 값 대응
    private String bcontent;
    private String bwriter;
    private String bdate;
}
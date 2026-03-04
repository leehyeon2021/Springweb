package example.day005.practice5;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookDto {
    private Integer bid;
    private String bname;
    private String bwriter;
    private String bpub;
}
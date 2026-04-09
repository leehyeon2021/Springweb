package manager.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import manager.entity.EmployeeEntity;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmployeeDto {
    private Integer eId;
    private String eName;
    private String eRank;
    private String eFile;

    private MultipartFile uploadFile;

    private Integer dId;
    private String dName;

    public EmployeeEntity toEntity(){
        return EmployeeEntity.builder()
                .eId(this.eId).eName(this.eName).eRank(this.eRank)
                .eFile(this.eFile)
                .build();
    }

}

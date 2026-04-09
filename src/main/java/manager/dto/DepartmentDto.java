package manager.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import manager.entity.DepartmentEntity;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DepartmentDto {
    private Integer dId;
    private String dName;

    public DepartmentEntity toEntity(){
        return DepartmentEntity.builder()
                .dId(this.dId).dName(this.dName)
                .build();
    }
}

package manager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import manager.dto.DepartmentDto;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DepartmentEntity {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer dId;
    @Column( length = 50 )
    private String dName;

    public DepartmentDto toDto(){
        return DepartmentDto.builder()
                .dId(this.dId).dName(this.dName)
                .build();
    }
}

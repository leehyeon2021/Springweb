package manager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import manager.dto.EmployeeDto;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "employee")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer eId;
    @Column(length = 50)
    private String eName;
    @Column(length = 20)
    private String eRank;

    @Column( nullable = true , length = 255 )
    private String eFile;

    @JoinColumn(name = "d_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private DepartmentEntity departmentEntity;

    public EmployeeDto toDto(){
        return EmployeeDto.builder()
                .eId(this.eId).eName(this.eName).eRank(this.eRank)
                .eFile(this.eFile)
                .dId(departmentEntity.getDId()).dName(departmentEntity.getDName())
                .build();
    }
}

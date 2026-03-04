package example.day005.practice5;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table( name = "book" )
@NoArgsConstructor @AllArgsConstructor @Data
public class BookEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer bid;

    @Column( name = "bname" , length = 100 , nullable = true)
    private String bname;

    @Column( name = "bwriter" , length = 50 , nullable = true)
    private String bwriter;

    @Column( name = "bpub" , length = 50 , nullable = true)
    private String bpub;

}

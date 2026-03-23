package springweb.member.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import springweb.member.dto.MemberDto;

@Entity @Table(name="member")
@NoArgsConstructor @AllArgsConstructor @Data @Builder
public class MemberEntity extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mno; // 회원번호

    @Column(nullable = false , unique = true , length = 100)
    private String mid; // 회원아이디

    @Column(nullable = false)
    private String mpwd; // 회원비밀번호

    @Column(nullable = false , length = 30)
    private String mname; // 회원닉네임

    // ENTITY -> DTO: 주로 조회에서 사용.
    public MemberDto toDto(){
        return MemberDto.builder()
                .mid(this.mid).mname(this.mname)
                .createDate(getCreateDate().toString()).updateDate(getUpdateDate().toString())
                //.mpwd(this.mpwd) <- 주로 비밀번호는 DTO로 반환하지 않는다. (웬만하면 비밀번호는 밖으로 안 뺌)
                .build();
    }
}

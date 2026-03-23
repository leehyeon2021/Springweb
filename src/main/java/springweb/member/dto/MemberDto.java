package springweb.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import springweb.member.entity.MemberEntity;

@NoArgsConstructor @AllArgsConstructor
@Data @Builder
public class MemberDto {
    private Long mno;
    private String mid;
    private String mpwd;
    private String mname;

    private String createDate;
    private String updateDate;

    // DTO -> ENTITY: 주로 저장, 수정에서 사용. / mno와 BaseTime은 자동이기에 안 넣어도 된다.
    public MemberEntity toEntity(){
        return MemberEntity.builder()
                .mid(this.mid).mpwd(this.mpwd).mname(this.mname)
                            .build();
    }
}
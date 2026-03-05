package example.day006.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data @Builder // 롬복
public class GoodsDto {
    private Integer gno;
    private String gname;
    private Integer gprice;
    private String gdesc;
    // + BaseTime 클래스에 추가한 시간 데이터 필드를 여기에다 추가해주기
    private String createDate;
    private String updateDate;

    // ⭐ Dto --> Entity 변환함수 (Dto에서 미리 해두기. (Service에서 직접변환하기도 하지만 편의상 Dto에 하나 봐.))
    public GoodsEntity toEntity(){
        // 빌더패턴이란? new 생성자가 아닌 함수로 객체 생성
        return GoodsEntity.builder() // 빌더 시작
                .gno( this.gno ).gname( this.gname ).gprice( this.gprice ).gdesc( this.gdesc )
                          .build();  // 빌더 끝
    }
}

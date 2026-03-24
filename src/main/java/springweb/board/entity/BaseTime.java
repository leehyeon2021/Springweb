package springweb.board.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

// ⭐⭐ BaseTime은 아래를 묶음으로 적어두고 복붙하기. + AppStart에 @EnableJpaAuditing까지 해줘야 한다!!

@MappedSuperclass // 엔티티 상속 용도 클래스라고 명시함
@Getter // 상속받은 엔티티가 멤버변수 사용 (GoodsEntity에서 이곳의 private를 검색하려고 씀.)

// @EntityListeners: 해당 엔티티 자동 감사 적용. (((엔티티가 생성되거나 수정될 때 써놔야하는 것!!)))
@EntityListeners( AuditingEntityListener.class ) // Auditing: 감시한다. -> Entity를 감시한다.

public class BaseTime {

    @CreatedDate // 엔티티 생성날짜 주입
    private LocalDateTime createDate;

    @LastModifiedDate // 엔티티 수정날짜 주입
    private LocalDateTime updateDate;

    // 이러케 나옴 : create_date datetime(6), 반영이 됨!! 신기하다
    // 이러케 나옴 : update_date datetime(6), 상속 관계로 엔티티 확장.
}

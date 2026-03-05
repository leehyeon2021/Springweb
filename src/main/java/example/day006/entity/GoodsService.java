package example.day006.entity;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service @Transactional
public class GoodsService {
    @Autowired
    private GoodsRepository goodsRepository;

    // 저장
    public boolean 저장( GoodsDto goodsDto ){
        // 1. dto --> entity 변환 (GoodsDto에 미리 해둬서 가져오기만 하면 됨)
        GoodsEntity goodsEntity = goodsDto.toEntity();
        // 2. JPA save 이용하여 엔티티 insert 하기
        GoodsEntity saved = goodsRepository.save( goodsEntity );
        // 3. save 결과 pk 여부 성공판단
        if( saved.getGno() >= 1 )return true;
        return false;

        // 한 줄로 만들면 (클린코딩!)
        //return goodsRepository.save( goodsEto.toEntity() ).getGno >= 1 ? true : false ;
    }

    // 수정 (@Transactional은 위에 써둠)
        // @Transactional: 수정시 여러 개 setter 사용-여러 개를 단일 실행으로 처리 가능.
    public boolean 수정( GoodsDto goodsDto ){
        // 1. 수정할 엔티티의 pk번호를 확인한다.
        int updatePk = goodsDto.getGno();
        // 2. 수정할 엔티티 찾기 --- 영속성 들어감! Optional로 포장해서 반환한다.
        Optional< GoodsEntity > optional = goodsRepository.findById( updatePk );
        // 3. 만약에 찾은 엔티티가 존재하면
        if( optional.isPresent() ){
            GoodsEntity updateEntity = optional.get(); // 4. 엔티티 꺼내기
            updateEntity.setGdesc(goodsDto.getGdesc());
            updateEntity.setGprice(goodsDto.getGprice());
            updateEntity.setGname(goodsDto.getGname()); // 3개 중에 setter에서 문제 발생시 전체 취소 된다. (@Transaction을 사용했음!)
            return true;
        }
        return false;
    }

}

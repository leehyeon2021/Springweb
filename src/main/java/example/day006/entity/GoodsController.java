package example.day006.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    // 저장
    @PostMapping
    public boolean 저장(@RequestBody GoodsDto goodsDto){
        boolean result = goodsService.저장(goodsDto);
        return result;
    }

    // 수정
    @PutMapping
    public  boolean 수정( @RequestBody GoodsDto goodsDto ){
        boolean result = goodsService.수정(goodsDto);
        return result;
    }

}


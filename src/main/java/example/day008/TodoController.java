package example.day008;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TodoController {
    @Autowired private TodoRepository tr;

    // {"title":"제목","content":"콘텐츠","done":"true"}
    @PostMapping("/")
    public boolean 등록하기(@RequestBody TodoEntity todoEntity) { // 원래 엔티티면 안 되는데 그냥 연습용으로 Service 없이 하는 중
        tr.save(todoEntity); return true;
    }

    @GetMapping("/")
    public List<TodoEntity> 조회하기(){
        return tr.findAll();
    }
}

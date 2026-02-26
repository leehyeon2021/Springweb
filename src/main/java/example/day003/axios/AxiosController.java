package example.day003.axios;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/day003/task")
public class AxiosController {
    // 4. AXIOS 사용법
    // 1.
    @GetMapping
    public void method1(){
        System.out.println("AxiosController.method1");
    }

    // 2.
    @DeleteMapping
    public int method2( @RequestParam String name ){
        System.out.println("AxiosController.method2");
        return 10;
    }

    // 3.
    @PostMapping
    public boolean method3( @RequestParam int age ){
        System.out.println("AxiosController.method3");
        return true;
    }

    // 4.
    @PutMapping
    public String method4(@RequestBody Map<String, Object> map ){
        System.out.println("AxiosController.method4");
        System.out.println("map = " + map);
        return "4번 Put 된다!";
    }

    // 5.
    @GetMapping("/axios")
    public String method5( @RequestParam String name ){
        System.out.println("AxiosController.method5");
        System.out.println("name = " + name);
        return "5번 Get 된다ㅎㅎ";
    }

}

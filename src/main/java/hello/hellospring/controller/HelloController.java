package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    
    // 이렇게 하면 웹 어플리케이션에서 /hello로 들어오면 여기 들어와짐
    @GetMapping("hello")
    public String hello(Model model){
        // model: key와 value로 이루어져있는 hashmap.
        // model.addAttribute() 통해 View에 전달할 데이터를 저장할 수 있음.
        model.addAttribute("data", "spring!!!");
        // 컨트롤러에서 리턴 값으로 문자를 반환하면, 뷰 리졸버가 화면을 찾아서 처리함.
        // return 'ViewName'
        // 'resources/template/'+{ViewName}+'.html'
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value="name",required=false) String name, Model model){
        model.addAttribute("name",name);
                return "hello-template";
    }

    @GetMapping("hello-spring")
    @ResponseBody 
    // HTML body부에 데이터를 그대로 넣어서 보냄 (View 없이)
    // @ResponseBody가 없으면, 컨트롤러는 viewResolver로 던짐. 이게 있으면 그냥 html 바디로 던져
    public String helloString(@RequestParam("name") String name){
        return "hello " + name;
    }

    // API방식
    @GetMapping("hello-api")
    @ResponseBody
    // @ResponseBody인데 객체가 반환되면, 객체를 JSON 형식으로 만들어서 반환해줌 hello(name:spring)
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello; //객체 반환 => JSON형식으로
    }

    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

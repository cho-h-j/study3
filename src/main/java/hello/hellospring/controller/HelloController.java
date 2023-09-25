package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","test!!");
        return "hello";///컨트롤러에서 리턴 값으로ㅜ 문자를 반환하면 뷰 리졸버가 화면을 찾아서 처리한다 (ㅋ스프링 부트 템플릿엔진 기본 viewName 맵핑  resource:templates/'{ViewwName}.html')
    }


    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model ){
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name")String name){
        ///http body 부분에 바로 ㄱㄱ 데이터 그대로 ㄱㄱ
        return "hello string : "+name;
    }


    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name, @RequestParam("age") int age){
        Hello hello = new Hello();
        hello.setName(name);
        hello.setAge(age);
        return hello;
    }


    static class Hello {

        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}

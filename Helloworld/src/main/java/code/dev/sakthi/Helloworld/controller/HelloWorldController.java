package code.dev.sakthi.Helloworld.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("/hello")
    public String SayHello(){
        return "Hello world";
    }

    @GetMapping("/bye")
    public String sayBye(){
        return "Bye bye !!";
    }


}

package ua.goit.dev6;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestIndexController {
    @GetMapping("/")
    @ResponseBody
    public String index(){
        return "Hello";
    }
}

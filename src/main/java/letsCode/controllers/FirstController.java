package letsCode.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@CrossOrigin
public class FirstController {

    @GetMapping("/main")
    public String mainPage(){
        return "/main";
    }

    @GetMapping("/table")
    public String tablePage(){
        return "/table";
    }

    @GetMapping("/hello")
    public String helloPage(Model model){
        String message = "Th Hello";
        model.addAttribute("message", message);
        return "first/hello";
    }

    @GetMapping("/goodbye")
    public String goodByePage(){
        return "first/goodbye";

    }
}

package letsCode.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class MainController {

    @GetMapping("/unsecured")
    public String unsecuredData(){
        System.out.println("UNSECURED");
        return "Unsecured data";
    }

    @GetMapping("/secured")
    public String securedData(){
        return "secured data";
    }

    @GetMapping("/admin")
    public String adminData(){
        return "Admin data";
    }

    /**
    @GetMapping("/info")
    public String userData(Principal principal){
        return principal.getName();
    }
    */
}

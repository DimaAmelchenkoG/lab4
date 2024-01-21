
package letsCode.controllers;


import jakarta.servlet.http.HttpServletResponse;
import letsCode.errors.AppError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import letsCode.dto.UserRequestDTO;
import letsCode.services.AuthService;


@RestController
@RequestMapping
@CrossOrigin
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService){
        this.authService = authService;
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRequestDTO request, HttpServletResponse response){
        System.out.println("REGISTER");
        //return new ResponseEntity<>(2, HttpStatus.OK);
        return authService.register(request, response);
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequestDTO request, HttpServletResponse response){
        System.out.println("login");
        return authService.login(request, response);
    }

    @PostMapping("/exit")
    public ResponseEntity<?> logout(HttpServletResponse response){
        System.out.println("exit");
        return authService.logout(response);
    }

}


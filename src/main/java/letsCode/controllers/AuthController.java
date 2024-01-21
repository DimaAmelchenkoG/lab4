
package letsCode.controllers;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import letsCode.errors.AppError;
import letsCode.models.PointEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import letsCode.dto.UserRequestDTO;
import letsCode.services.AuthService;

import java.util.ArrayList;
import java.util.List;


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
    public ResponseEntity<?> login(@RequestBody UserRequestDTO request, HttpServletRequest httpServletRequest, HttpServletResponse response){
        System.out.println("login");
        List<PointEntity> list = new ArrayList<>();
        PointEntity point = new PointEntity();
        point.setX(1);
        point.setY(2);
        point.setR(3);
        list.add(point);
        list.add(point);
        //return new ResponseEntity<>(list, HttpStatus.OK);

        return authService.login(request, httpServletRequest, response);
    }

    @PostMapping("/exit")
    public ResponseEntity<?> logout(HttpServletResponse response){
        System.out.println("exit");
        return authService.logout(response);
    }

}



package letsCode.services;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import letsCode.dto.PointResponseDTO;
import letsCode.models.PointEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import letsCode.utils.JwtTokenUtils;
import letsCode.models.User;
import letsCode.dto.UserRequestDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthService {

    private final MyUserService myUserService;
    private final PointService pointService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthService(MyUserService myUserService, PointService pointService, PasswordEncoder passwordEncoder,
                       JwtTokenUtils jwtTokenUtils,
                       AuthenticationManager authenticationManager){
        this.myUserService = myUserService;
        this.pointService = pointService;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtils = jwtTokenUtils;
        this.authenticationManager = authenticationManager;
    }

    public ResponseEntity<?> register( @RequestBody UserRequestDTO request, HttpServletResponse response){

        String userName = request.getUserName();
        String password = passwordEncoder.encode(request.getPassword());
        boolean userIsHas = myUserService.findByLogin(userName);
        if(userIsHas){
            User user = new User();
            user.setUsername(userName);
            user.setPassword(password);
            myUserService.addUserToDB(user);
            String token = jwtTokenUtils.generateToken(myUserService.loadUserByUsername(userName));
            Cookie cookieToken = new Cookie("Token", token);
            cookieToken.setPath("/");
            cookieToken.setMaxAge(3600);
            cookieToken.setHttpOnly(true);
            response.addCookie(cookieToken);
            //return new ResponseEntity<>(getAllResults((HttpServletRequest) request), HttpStatus.CREATED);
            return ResponseEntity.ok(jwtTokenUtils.getLifeTime(token));
        }
        else {
            System.out.println("User with name " + userName + " exists");
            //return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "User with name " + userName + " exists"), HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>("User with name " + userName + " exists", HttpStatus.BAD_REQUEST);
        }


    }

    public ResponseEntity<?> getAllResults(HttpServletRequest servletRequest){
        List<PointEntity> results = pointService.getResults(myUserService.getUserByLogin(jwtTokenUtils.getUserName(jwtTokenUtils.getToken(servletRequest))).getId());
        List<PointResponseDTO> mappedResults = mapToNeedResults(results);
        return ResponseEntity.ok(mappedResults);
    }

    private List<PointResponseDTO> mapToNeedResults(List<PointEntity> results){
        return results.stream().map(pointEntity -> {
            PointResponseDTO dto = new PointResponseDTO();
            dto.setX(pointEntity.getX());
            dto.setY(pointEntity.getY());
            dto.setR(pointEntity.getR());
            dto.setCurrentTime(pointEntity.getCurrentTime());
            dto.setExecutionTime(pointEntity.getExecutionTime());
            dto.setResult(pointEntity.isResult());
            return dto;
        }).collect(Collectors.toList());
    }

    public ResponseEntity<?> login( @RequestBody UserRequestDTO request, HttpServletRequest httpServletRequest, HttpServletResponse response){
        String userName = request.getUserName();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, request.getPassword()));
        }catch (AuthenticationException ex){
            System.out.println("НЕВЕРНЫЙ ЛОГИН ИЛИ ПАРОЛЬ");
           // return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "Пароль или логи введены неверно"), HttpStatus.UNAUTHORIZED);
            return new ResponseEntity<>("Пароль или логи введены неверно", HttpStatus.UNAUTHORIZED);
        }
        System.out.println("no ok ");
        String token = jwtTokenUtils.generateToken(myUserService.loadUserByUsername(userName));
        Cookie cookieToken = new Cookie("Token", token);
        cookieToken.setPath("/");
        cookieToken.setMaxAge(3600);
        cookieToken.setHttpOnly(true);
        response.addCookie(cookieToken);
        List<PointEntity> list = new ArrayList<>();
        //return new ResponseEntity<>(getAllResults(httpServletRequest), HttpStatus.OK);
        return ResponseEntity.ok(jwtTokenUtils.getLifeTime(token));


    }

    public ResponseEntity<?> logout(HttpServletResponse response ){
        Cookie cookie = new Cookie("Token", "");
        cookie.setMaxAge(1);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return  ResponseEntity.ok(new Date().getTime()
        );
    }
}

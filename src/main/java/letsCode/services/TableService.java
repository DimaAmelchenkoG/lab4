package letsCode.services;


import jakarta.servlet.http.HttpServletRequest;
import letsCode.otherModels.RequestPoint;
import letsCode.otherModels.PesponsePoint;
import letsCode.models.MyPoint;
import letsCode.models.User;
import letsCode.utils.CheckTarget;
import letsCode.utils.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TableService {

    private final MyUserService myUserService;
    private final PointService pointService;
    private final JwtTokenUtils jwtTokenUtils;



    @Autowired
    public TableService(PointService pointService, MyUserService myUserService, JwtTokenUtils jwtTokenUtils){
        this.pointService = pointService;
        this.myUserService = myUserService;
        this.jwtTokenUtils = jwtTokenUtils;
    }

    public ResponseEntity<?> addPoint(@RequestBody RequestPoint requestDTO, HttpServletRequest servletRequest){
        User user = myUserService.getUserByLogin(jwtTokenUtils.getUserName(jwtTokenUtils.getToken(servletRequest)));
        MyPoint myPoint = new MyPoint();
        String x = (requestDTO.getX()).replace(",", ".");
        String y = "";
        try {
            y = (requestDTO.getY()).replace(",", ".");
        }catch (NumberFormatException ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        double r = requestDTO.getR();
        if (Double.parseDouble(x) > -5 && Double.parseDouble(x) < 5 && Double.parseDouble(y) > -4 && Double.parseDouble(y) < 4){
            myPoint.setUserId(user.getId());
            myPoint.setX(x);
            myPoint.setY(y);
            myPoint.setR(r);
            long startExecution = System.nanoTime();
            LocalDateTime localDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd 'Time: 'HH:mm:ss");
            String timeNow = localDateTime.format(formatter);
            myPoint.setCurrentTime(timeNow.split(": ")[1]);
            long executionEnd = System.nanoTime() - startExecution;
            boolean result = CheckTarget.checkResult(requestDTO.getX().replace(",", "."), requestDTO.getY().replace(",", "."), requestDTO.getR());
            myPoint.setResult(result);
            myPoint.setExecutionTime(executionEnd);
            pointService.addPoint(myPoint, user.getId());
            PesponsePoint pointResultResponseDTO = new PesponsePoint(x, y, r,
                    timeNow, executionEnd, result);
            return new ResponseEntity<>(getPoints(servletRequest), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<?> getPoints(HttpServletRequest servletRequest){
        List<MyPoint> results = pointService.getResults(myUserService.getUserByLogin(jwtTokenUtils.getUserName(jwtTokenUtils.getToken(servletRequest))).getId());
        List<PesponsePoint> mappedResults = getListOfPoints(results);
        return ResponseEntity.ok(mappedResults);
    }


    public ResponseEntity<?> delete(HttpServletRequest servletRequest){
        pointService.delete(myUserService.getUserByLogin(jwtTokenUtils.getUserName(jwtTokenUtils.getToken(servletRequest))).getId());
        return ResponseEntity.ok(HttpStatus.OK);
    }


    private List<PesponsePoint> getListOfPoints(List<MyPoint> results){
        return results.stream().map(pointEntity -> {
            PesponsePoint dto = new PesponsePoint();
            dto.setX(pointEntity.getX());
            dto.setY(pointEntity.getY());
            dto.setR(pointEntity.getR());
            dto.setCurrentTime(pointEntity.getCurrentTime());
            dto.setExecutionTime(pointEntity.getExecutionTime());
            dto.setResult(pointEntity.isResult());
            return dto;
        }).collect(Collectors.toList());
    }

}
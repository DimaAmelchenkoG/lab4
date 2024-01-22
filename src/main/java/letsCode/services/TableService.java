package letsCode.services;


import jakarta.servlet.http.HttpServletRequest;
import letsCode.otherModels.RequestPoint;
import letsCode.otherModels.pesponsePoint;
import letsCode.models.MyPoint;
import letsCode.models.User;
import letsCode.utils.CheckTarget;
import letsCode.utils.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


import java.text.DecimalFormat;
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

    public ResponseEntity<?> addPointToTable(@RequestBody RequestPoint requestDTO, HttpServletRequest servletRequest){
        User user = myUserService.getUserByLogin(jwtTokenUtils.getUserName(jwtTokenUtils.getToken(servletRequest)));
        MyPoint myPoint = new MyPoint();
        //DecimalFormat decimalFormat = new DecimalFormat("#.###");
        String x = (requestDTO.getX()).replace(",", ".");
        String y = "";
        try {
            y = (requestDTO.getY()).replace(",", ".");
        }catch (NumberFormatException ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        double r = requestDTO.getR();
        //if(validateDots(x, y, r)) {
        if (true){
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
            pointService.addPointsResultToDB(myPoint, user.getId());
            pesponsePoint pointResultResponseDTO = new pesponsePoint(x, y, r,
                    timeNow, executionEnd, result);
            return new ResponseEntity<>(getAllResults(servletRequest), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<?> getAllResults(HttpServletRequest servletRequest){
        List<MyPoint> results = pointService.getResults(myUserService.getUserByLogin(jwtTokenUtils.getUserName(jwtTokenUtils.getToken(servletRequest))).getId());
        List<pesponsePoint> mappedResults = mapToNeedResults(results);
        return ResponseEntity.ok(mappedResults);
    }


    public ResponseEntity<?> deleteDots(HttpServletRequest servletRequest){
        pointService.deleteResults(myUserService.getUserByLogin(jwtTokenUtils.getUserName(jwtTokenUtils.getToken(servletRequest))).getId());
        return ResponseEntity.ok(HttpStatus.OK);
    }


    private List<pesponsePoint> mapToNeedResults(List<MyPoint> results){
        return results.stream().map(pointEntity -> {
            pesponsePoint dto = new pesponsePoint();
            dto.setX(pointEntity.getX());
            dto.setY(pointEntity.getY());
            dto.setR(pointEntity.getR());
            dto.setCurrentTime(pointEntity.getCurrentTime());
            dto.setExecutionTime(pointEntity.getExecutionTime());
            dto.setResult(pointEntity.isResult());
            return dto;
        }).collect(Collectors.toList());
    }

    private boolean validateDots(double x, double y, double r){
        return (x >= -5 && x <= 3) && (y>=-3 && y <=3) && (r>0);

    }

}
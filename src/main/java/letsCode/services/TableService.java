package letsCode.services;


import jakarta.servlet.http.HttpServletRequest;
import letsCode.dto.PointRequestDTO;
import letsCode.dto.PointResponseDTO;
import letsCode.models.PointEntity;
import letsCode.models.User;
import letsCode.utils.AreaResultCheck;
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

    public ResponseEntity<?> addPointToTable(@RequestBody PointRequestDTO requestDTO, HttpServletRequest servletRequest){
        User user = myUserService.getUserByLogin(jwtTokenUtils.getUserName(jwtTokenUtils.getToken(servletRequest)));
        PointEntity pointEntity = new PointEntity();
        DecimalFormat decimalFormat = new DecimalFormat("#.###");
        double x = Double.parseDouble(decimalFormat.format(requestDTO.getX()).replace(",", "."));
        double y = 0;
        try {
            y = Double.parseDouble(decimalFormat.format(requestDTO.getY()).replace(",", "."));
        }catch (NumberFormatException ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        double r = requestDTO.getR();
        //if(validateDots(x, y, r)) {
        if (true){
            pointEntity.setUserId(user.getId());
            pointEntity.setX(x);
            pointEntity.setY(y);
            pointEntity.setR(r);
            long startExecution = System.nanoTime();
            LocalDateTime localDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd 'Time: 'HH:mm:ss");
            String timeNow = localDateTime.format(formatter);
            pointEntity.setCurrentTime(timeNow);
            long executionEnd = System.nanoTime() - startExecution;
            boolean result = AreaResultCheck.checkResult(requestDTO.getX(), requestDTO.getY(), requestDTO.getR());
            pointEntity.setResult(result);
            pointEntity.setExecutionTime(executionEnd);
            pointService.addPointsResultToDB(pointEntity, user.getId());
            PointResponseDTO pointResultResponseDTO = new PointResponseDTO(x, y, r,
                    timeNow, executionEnd, result);
            return new ResponseEntity<>(getAllResults(servletRequest), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<?> getAllResults(HttpServletRequest servletRequest){
        List<PointEntity> results = pointService.getResults(myUserService.getUserByLogin(jwtTokenUtils.getUserName(jwtTokenUtils.getToken(servletRequest))).getId());
        List<PointResponseDTO> mappedResults = mapToNeedResults(results);
        return ResponseEntity.ok(mappedResults);
    }


    public ResponseEntity<?> deleteDots(HttpServletRequest servletRequest){
        pointService.deleteResults(myUserService.getUserByLogin(jwtTokenUtils.getUserName(jwtTokenUtils.getToken(servletRequest))).getId());
        return ResponseEntity.ok(HttpStatus.OK);
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

    private boolean validateDots(double x, double y, double r){
        return (x >= -5 && x <= 3) && (y>=-3 && y <=3) && (r>0);

    }

}
package letsCode.services;


import letsCode.errors.AppError;
import letsCode.models.PointEntity;
import letsCode.repositories.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.List;

@Service
public class PointService {

    private final PointRepository pointRepository;


    @Autowired
    public PointService(PointRepository pointRepository){
        this.pointRepository = pointRepository;
    }


    @Transactional
    public List<PointEntity> getResults(Long userId){
        return pointRepository.getAllByUserId(userId);
    }

    @Transactional
    public void addPointsResultToDB(PointEntity results, Long userId){
        results.setUserId(userId);
        pointRepository.save(results);
    }

    @Transactional
    public int deleteResults(Long userId){
        int countDelete = pointRepository.deleteAllByUserId(userId);
        if(countDelete>0){
            return countDelete;
        }

        return new AppError(500, "Warn, failed to delete results").getStatus();
    }

}
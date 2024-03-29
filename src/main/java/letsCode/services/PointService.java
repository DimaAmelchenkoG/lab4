package letsCode.services;


import letsCode.errors.ErrorMessage;
import letsCode.models.MyPoint;
import letsCode.repositories.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PointService {

    private final PointRepository pointRepository;


    @Autowired
    public PointService(PointRepository pointRepository){
        this.pointRepository = pointRepository;
    }


    @Transactional
    public List<MyPoint> getResults(Long userId){
        return pointRepository.getAllByUserId(userId);
    }

    @Transactional
    public void addPoint(MyPoint results, Long userId){
        results.setUserId(userId);
        pointRepository.save(results);
    }

    @Transactional
    public int delete(Long userId){
        int countDelete = pointRepository.deleteAllByUserId(userId);
        if(countDelete>0){
            return countDelete;
        }

        return new ErrorMessage(500, "Warn, failed to delete results").getStatus();
    }

}
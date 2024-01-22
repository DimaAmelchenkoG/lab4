package letsCode.repositories;

import letsCode.models.MyPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface PointRepository extends JpaRepository<MyPoint, Long> {

    List<MyPoint> getAllByUserId(Long userId);
    int deleteAllByUserId(Long userId);

}
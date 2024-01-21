package letsCode.repositories;

import letsCode.models.PointEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface PointRepository extends JpaRepository<PointEntity, Long> {

    List<PointEntity> getAllByUserId(Long userId);
    int deleteAllByUserId(Long userId);

}
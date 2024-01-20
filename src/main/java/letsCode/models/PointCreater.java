
package letsCode.models;

import java.time.LocalTime;

public class PointCreater {

    public PointTest createPoint(PointTest pointTest, long t1){
        TargetChecker targetChecker = new TargetChecker();
        //System.out.println(point.getX() + " " + point.getY() + " " + point.getR() + " " +point.getTime() + " " +  point.getDate() + " " +point.getTarget());
        pointTest.setX(pointTest.getX().replace(",", "."));
        pointTest.setTarget(targetChecker.checkAll(pointTest));
        pointTest.setTimeNow(String.valueOf(LocalTime.now().withNano(0)));
        pointTest.setProgramTime(String.format("%.5f", Float.valueOf(System.nanoTime() - t1) / 1000000000));
        return pointTest;
    }

}


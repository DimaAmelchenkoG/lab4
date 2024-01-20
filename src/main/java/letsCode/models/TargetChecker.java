package letsCode.models;

public class TargetChecker {

    public String check(PointTest pointTest){
        if (Integer.parseInt(pointTest.getX()) >= 0){
            return "Попадание";
        }
        else {
            return "Нет попадания";
        }
    }

    public String checkAll(PointTest pointTest){
        System.out.println(pointTest.getX());
        Float x = Float.valueOf(pointTest.getX());
        Float y =  Float.valueOf(String.valueOf(pointTest.getY()));
        System.out.println(pointTest.getR());
        Float r = Float.valueOf(pointTest.getR());;
        if  (checkSquare(x, y, r) | checkTriangle(x, y, r) | checkCircle(x, y, r)){
            return "Hit";
        }
        else {
            return "No hit";
        }
    }

    private boolean checkSquare(Float x, Float y, Float r){
        //System.out.println((x <= 0 & x <= r/2) & (y <= 0 & y <= r));
        return (x <= 0 & x >= -r/2) & (y <= 0 & y >= -r);
    }

    private boolean checkTriangle(Float x, Float y, Float r){
        //System.out.println(x + " " + y + " " + r);
        //System.out.println( (x >= 0 & y >= 0) & ((x*x + y*y) <= r*r));
        return (x >= 0 & y >= 0) & (y <= r & x <= r) & (x + y <= r);
    }

    private boolean checkCircle(Float x, Float y, Float r){
        //System.out.println((x <= 0 & y >= 0) & ((x*x + y*y) <= (r*r)/4));
        return (x <= 0 & y >= 0) & ((x*x + y*y) <= (r*r)/4);
    }

}

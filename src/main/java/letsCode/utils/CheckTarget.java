package letsCode.utils;

public class CheckTarget {

    public static boolean checkResult(String xString, String yString, double r){
        Double x = Double.parseDouble(xString);
        Double y = Double.parseDouble(yString);
        return (checkSquare(x, y, r) | checkTriangle(x, y, r) | checkCircle(x, y, r));
    }

    private static boolean checkSquare(double x, double y, double r){
        return (x <= 0 & x >= -r/2) & (y <= 0 & y >= -r);
    }

    private static boolean checkTriangle(double x, double y, double r){
        return (x >= 0 & y >= 0) & (y <= r & x <= r) & (x + y <= r);
    }

    private static boolean checkCircle(double x, double y, double r){
        return (x <= 0 & y >= 0) & ((x*x + y*y) <= (r*r)/4);
    }
}
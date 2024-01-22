package letsCode.otherModels;

import java.util.Objects;

public class RequestPoint {

    private String x;
    private String y;
    private double r;

    public RequestPoint(){}

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestPoint that = (RequestPoint) o;
        return Objects.equals(x, that.x) && Objects.equals(y, that.y) && Double.compare(r, that.r) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, r);
    }
}
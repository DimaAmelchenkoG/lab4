
package letsCode.models;

public class Point {
    private String x;
    private Double y;
    private String r;

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public String getR() {
        return r;
    }

    public void setR(String r) {
        this.r = r;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x='" + x + '\'' +
                ", y='" + y + '\'' +
                ", r='" + r + '\'' +
                '}';
    }
}

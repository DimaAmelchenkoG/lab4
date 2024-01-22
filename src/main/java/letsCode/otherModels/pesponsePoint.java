package letsCode.otherModels;

import java.util.Objects;

public class pesponsePoint {
    private String x;
    private String y;
    private double r;
    private String currentTime;

    private long executionTime;

    private boolean result;

    public pesponsePoint(){}
    public pesponsePoint(String x, String y, double r, String currentTime, long executionTime, boolean result) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.currentTime = currentTime;
        this.executionTime = executionTime;
        this.result = result;
    }


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

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        pesponsePoint that = (pesponsePoint) o;
        return Objects.equals(x, that.x) && Objects.equals(y, that.y) && Double.compare(r, that.r) == 0 && executionTime == that.executionTime && result == that.result && Objects.equals(currentTime, that.currentTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, r, currentTime, executionTime, result);
    }
}
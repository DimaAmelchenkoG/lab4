package letsCode.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tableOfPoints")
public class MyPoint implements Serializable {

    @GeneratedValue
    @Column
    @Id
    private long id;

    @Column(name = "user_id")
    private long userId;

    @Column
    private String x;
    @Column
    private String y;
    @Column
    private double r;
    @Column(name = "local_date_time")
    private String currentTime;
    @Column(name = "execution_time")
    private long executionTime;
    @Column
    private boolean result;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    public MyPoint(){}
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyPoint that = (MyPoint) o;
        return id == that.id && userId == that.userId && Objects.equals(x, that.x) && Objects.equals(y, that.y) && Double.compare(r, that.r) == 0 && executionTime == that.executionTime && result == that.result && Objects.equals(currentTime, that.currentTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, x, y, r, currentTime, executionTime, result);
    }
}
package letsCode.models;

import jakarta.persistence.*;

@Entity
@Table(name = "points")
public class PointTest {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @Id
    private long id;

    @Column
    private long user_id;

    @Column
    private String x;

    @Column
    private String y;

    @Column
    private String r;

    @Column
    private String target;

    @Column
    private String timeNow;

    @Column
    private String programTime;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
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

    public String getR() {
        return r;
    }

    public void setR(String r) {
        this.r = r;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getTimeNow() {
        return timeNow;
    }

    public void setTimeNow(String timeNow) {
        this.timeNow = timeNow;
    }

    public String getProgramTime() {
        return programTime;
    }

    public void setProgramTime(String programTime) {
        this.programTime = programTime;
    }

    @Override
    public String toString() {
        return "PointTest{" +
                "id=" + id +
                ", x='" + x + '\'' +
                ", y='" + y + '\'' +
                ", r='" + r + '\'' +
                ", target='" + target + '\'' +
                '}';
    }
}

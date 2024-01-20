
package letsCode.models;

import jakarta.persistence.*;

@Entity
@Table(name = "points")
public class PointForDB {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Id
    private long id;

    @Column(name = "x")
    private String x;
    @Column(name = "yT")
    private String y;
    @Column(name = "r")
    private String r;


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
}

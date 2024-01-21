
package letsCode.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "points")
@AllArgsConstructor
public class PointForDB implements Serializable {
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

    public PointForDB() {

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
}

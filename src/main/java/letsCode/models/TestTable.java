package letsCode.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "testTable")
public class TestTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "test")
    private String name;

    @Column(name = "testTV")
    private String testTV;
}

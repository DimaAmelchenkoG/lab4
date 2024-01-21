package letsCode.dao;

import letsCode.models.PointTest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PointDAO {
    private static int PEOPLE_COUNT;
    private List<PointTest> points;
    {
        points = new ArrayList<>();
    }
    /**
    {
        people = new ArrayList<>();
        people.add(new Point(++PEOPLE_COUNT, "Tom"));
        people.add(new Point(++PEOPLE_COUNT, "Bob"));
        people.add(new Point(++PEOPLE_COUNT, "Mike"));
    }
     */

    public List<PointTest> index(){
        return points;
    }

    public PointTest show(int id){
        return points.stream().filter(point -> point.getId() == id).findAny().orElse(null);
    }

    public String showAll(){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < points.size(); i++){
            stringBuilder.append(points.get(i).toString());
        }
        return stringBuilder.toString();
    }

    public void save(PointTest pointTest){
        pointTest.setId(++PEOPLE_COUNT);
        points.add(pointTest);
    }

    public void delete(){
        points.clear();
    }

    public void print(){
        for (int i = 0; i < points.size(); i++){
            System.out.println(points.get(i));
        }
    }
}

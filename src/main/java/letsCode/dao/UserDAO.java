package letsCode.dao;

import letsCode.models.UserTest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAO {
    private static int USER_COUNT;
    private List<UserTest> users;
    {
        users = new ArrayList<>();
    }
    /**
     {
     people = new ArrayList<>();
     people.add(new Point(++PEOPLE_COUNT, "Tom"));
     people.add(new Point(++PEOPLE_COUNT, "Bob"));
     people.add(new Point(++PEOPLE_COUNT, "Mike"));
     }
     */

    public List<UserTest> index(){
        return users;
    }

    public UserTest show(int id){
        return users.stream().filter(userTest -> userTest.getId() == id).findAny().orElse(null);
    }

    public String showAll(){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < users.size(); i++){
            stringBuilder.append(users.get(i).toString());
        }
        return stringBuilder.toString();
    }

    public void save(UserTest userTest){
        userTest.setId(++USER_COUNT);
        users.add(userTest);
    }

    public void delete(){
        users.clear();
    }

    public void print(){
        for (int i = 0; i < users.size(); i++){
            System.out.println(users.get(i));
        }
    }
}

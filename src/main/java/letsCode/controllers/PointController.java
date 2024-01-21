package letsCode.controllers;

import jakarta.servlet.http.HttpServletRequest;
//import letsCode.dao.PointDAO;
import letsCode.dao.PointDAO;
import letsCode.dao.UserDAO;
import letsCode.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
//import letsCode.models.PointCreater;

@RestController
@RequestMapping("/mypoints")
@CrossOrigin
public class PointController {

    private final PointDAO pointDAO;
    private final UserDAO userDAO;


    @Autowired
    public PointController(PointDAO pointDAO, UserDAO userDAO){
        this.pointDAO = pointDAO;
        this.userDAO = userDAO;
    }


    @GetMapping()
    public String index(Model model){
        System.out.println("INDEX");
        model.addAttribute("points", pointDAO.index());
        model.addAttribute("point", new Point());
        return "mypoints/test";
    }

    /**

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("point", pointDAO.show(id));
        return "mypoints/show";
    }
*/

    /**
    @PostMapping("/new")
     @CrossOrigin
    public ResponseEntity<?> register(){
        System.out.println("NEW");
        return ResponseEntity.ok(HttpStatus.OK);
    }
     */


    @PostMapping("/add")
    @CrossOrigin
    public ResponseEntity<?> add(@RequestBody PointTest pointTest, HttpServletRequest httpServletRequest){
        PointForDB pointForDB = new PointForDB(1, "10", "20", "30");
        //Role role = new Role();
        //role.setId(1);
        //role.setName("Hello");

        long t1 = System.nanoTime();
        PointCreater pointCreater = new PointCreater();
        pointCreater.createPoint(pointTest, t1);
        System.out.println(pointTest.toString());
        System.out.println("Add new");
        pointDAO.save(pointTest);
        pointDAO.print();
        return new ResponseEntity<>(pointDAO.index(), HttpStatus.OK);
    }

    @PostMapping("/reg")
    @CrossOrigin
    public ResponseEntity<?> reg(@RequestBody UserTest userTest, HttpServletRequest httpServletRequest){
        System.out.println("REG");
        userTest.setStatus("УРАААААА");
        userDAO.save(userTest);
        return new ResponseEntity<>(userDAO.index(), HttpStatus.OK);
    }

    /**@GetMapping("/new")
    public String newPoint(Model model){
        System.out.println("NEWPOINT");
        //model.addAttribute("point", new Point());
        return "mypoints/new";
    }
     */
/**
    @PostMapping()
    public String create(@ModelAttribute("point") Point point){
        long t1 = System.nanoTime();
        PointCreater pointCreater = new PointCreater();
        point = pointCreater.createPoint(point, t1);

        System.out.println("CREATE");
        pointDAO.save(point);
        return "redirect:/mypoints";
    }
 */
    @PostMapping("/clean")
    @CrossOrigin
    public  ResponseEntity<?> clean(){
        System.out.println("DELETE");
        pointDAO.delete();
        return ResponseEntity.ok(HttpStatus.OK);
    }

}

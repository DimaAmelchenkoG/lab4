package letsCode.controllers;

import jakarta.servlet.http.HttpServletRequest;
import letsCode.otherModels.RequestPoint;
import letsCode.models.*;
import letsCode.services.TableService;
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



    private final TableService tableService;


    @Autowired
    public PointController(TableService tableService){
        this.tableService = tableService;
    }


    @GetMapping("/all")
    public ResponseEntity<?> all(HttpServletRequest servletRequest){
        System.out.println("ADD DOT");
        return tableService.getPoints(servletRequest);
    }

    @PostMapping("addPoint")
    public ResponseEntity<?> addPointToTable(@RequestBody RequestPoint requestDTO, HttpServletRequest servletRequest){
        System.out.println("ADD DOT");
        return tableService.addPoint(requestDTO, servletRequest);
    }

    @PostMapping("/cleanTable")
    public ResponseEntity<?> deleteDots(HttpServletRequest servletRequest){
        System.out.println("CLEAN");
        return tableService.delete(servletRequest);
    }

    @GetMapping()
    public String index(Model model){
        System.out.println("INDEX");
        model.addAttribute("points");
        model.addAttribute("point");
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

        long t1 = System.nanoTime();
        PointCreater pointCreater = new PointCreater();
        pointCreater.createPoint(pointTest, t1);
        System.out.println(pointTest.toString());
        System.out.println("Add new");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/reg")
    @CrossOrigin
    public ResponseEntity<?> reg(HttpServletRequest httpServletRequest){
        return new ResponseEntity<>(HttpStatus.OK);
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
        return ResponseEntity.ok(HttpStatus.OK);
    }

}

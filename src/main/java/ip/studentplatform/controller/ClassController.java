package ip.studentplatform.controller;

import ip.studentplatform.entity.Materie;
import ip.studentplatform.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/class")
public class ClassController {
    @Autowired
    ClassService classService;

    @PutMapping("/addStudent")
    public void addStudent(@RequestParam(name = "name") String name, @RequestParam(name = "nameMaterie") String nameMaterie) {
        List<Materie> materies = this.classService.findListMaterie(name);
        materies.add(this.classService.getMaterie(nameMaterie));
        this.classService.addStudentClass(name, materies);
    }
}

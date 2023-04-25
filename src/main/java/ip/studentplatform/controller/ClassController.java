package ip.studentplatform.controller;

import ip.studentplatform.entity.Materie;
import ip.studentplatform.entity.Professor;
import ip.studentplatform.service.ClassService;
import ip.studentplatform.service.EmailSenderService;
import ip.studentplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/class")
public class ClassController {
    @Autowired
    ClassService classService;

    @Autowired
    UserService userService;

    @Autowired
    private EmailSenderService senderService;

    @GetMapping("/here")
    public void email() {
        senderService.sendSimpleEmail("eduard.costin8@gmail.com",
                "This is email body",
                "This is email subject");
    }

    @PutMapping("/addStudent")
    public void addStudent(@RequestParam(name = "name") String name, @RequestParam(name = "nameMaterie") String nameMaterie) {
        List<Materie> materies = this.classService.findListMaterie(name);
        Materie materie = this.classService.getMaterie(nameMaterie);
        for (Materie materieSearch :materies) {
            if(materieSearch.getId_mat() == materie.getId_mat()) {
                return;
            }
        }
        materies.add(materie);
        this.classService.addStudentClass(name, materies);
    }

    @PutMapping("/addProfessorToClass")
    public void addProfessor(@RequestParam(name = "name") String name, @RequestParam(name = "numeMaterie") String nameMaterie) {
        Materie materie = this.classService.getMaterie(nameMaterie);
        List<Materie> materies = this.userService.findListMaterieProfessor(name);
        this.classService.updateMaterie(materie.getId_mat(), this.userService.findByFirstname(name));
        for (Materie materieSearch :materies) {
            if(materieSearch.getId_mat() == materie.getId_mat()) {
                return;
            }
        }
        materies.add(materie);
        System.out.println(materie.getId_mat());
        this.userService.updateProfessor(name, materies);

    }
}

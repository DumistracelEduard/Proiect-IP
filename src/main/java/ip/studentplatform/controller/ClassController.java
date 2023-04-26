package ip.studentplatform.controller;

import ip.studentplatform.entity.Materie;
import ip.studentplatform.entity.Professor;
import ip.studentplatform.service.ClassService;
import ip.studentplatform.service.EmailSenderService;
import ip.studentplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasAuthority('admin')")
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

    @PreAuthorize("hasAuthority('admin')")
    @PutMapping("/addProfessorToClass")
    public void addProfessor(@RequestParam(name = "name") String name, @RequestParam(name = "numeMaterie") String nameMaterie) {
        Materie materie = this.classService.getMaterie(nameMaterie);
        List<Materie> materies = this.userService.findListMaterieProfessor(name);

        for (Materie materieSearch :materies) {
            if(materieSearch.getId_mat() == materie.getId_mat()) {
                return;
            }
        }
        this.classService.updateMaterie(materie.getId_mat(), this.userService.findByFirstname(name));
        materies.add(materie);
        System.out.println(materie.getId_mat());
        this.userService.updateProfessor(name, materies);

    }
}

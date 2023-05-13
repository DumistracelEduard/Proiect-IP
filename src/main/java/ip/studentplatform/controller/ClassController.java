package ip.studentplatform.controller;

import ip.studentplatform.entity.Admin;
import ip.studentplatform.entity.Materie;
import ip.studentplatform.entity.Professor;
import ip.studentplatform.entity.Student;
import ip.studentplatform.service.ClassService;
import ip.studentplatform.service.EmailSenderService;
import ip.studentplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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
        this.classService.updateMaterie(materie.getId_mat(), this.userService.findByFirstname(name));
        materies.add(materie);
    }

    @GetMapping("/getMaterie")
    public List<String> getAllMaterie() {
        List<Materie> materies = this.classService.getListMaterie();
        List<String> data = new ArrayList<>();
        for(Materie materie: materies) {
            data.add(materie.getNameMaterie());
        }
        return data;
    }

    @PreAuthorize("hasAuthority('student')")
    @PutMapping("/addFlag")
    public void putFlagGrade(@RequestParam("materie") String nameMaterie, Principal principal) {
        Student student = (Student) this.userService.getUser(principal.getName());
        Materie materie = this.classService.getMaterie(nameMaterie);
        this.classService.addFlagGrade(student, materie, true);
        List<Admin> adminList = this.userService.getListAdmin();
        for(Admin admin: adminList) {
            senderService.sendSimpleEmail(admin.getEmail(),
                    "Update Grades",
                    "Student " + student.getFirstName() + " " + student.getLastName() + " update grade " + nameMaterie);
        }
        Professor professor = materie.getProfessor();
        senderService.sendSimpleEmail(professor.getEmail(),
                "Update Grades",
                "Student " + student.getFirstName() + " " + student.getLastName() + " update grade " + nameMaterie);
    }
}

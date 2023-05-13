package ip.studentplatform.controller;

import ip.studentplatform.entity.Grade;
import ip.studentplatform.entity.Materie;
import ip.studentplatform.entity.Student;
import ip.studentplatform.entity.User;
import ip.studentplatform.service.ClassService;
import ip.studentplatform.service.EmailSenderService;
import ip.studentplatform.service.GradesService;
import ip.studentplatform.service.UserService;
import jakarta.persistence.MappedSuperclass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/Grades")
public class GradesController {
    @Autowired
    GradesService gradesService;

    @Autowired
    private EmailSenderService senderService;

    @Autowired
    UserService userService;

    @Autowired
    ClassService classService;

    @PreAuthorize("hasAuthority('admin')")
    @PutMapping("/approvedGrades")
    public void approvedGrades(@RequestParam("firstName") String firstName,
                               @RequestParam("lastName") String lastName,
                               @RequestParam("materie") String materie) {
        this.gradesService.approvedGrades(firstName, lastName, materie);
        User user = (User) this.userService.getStudent(firstName, lastName);
        senderService.sendSimpleEmail(user.getEmail(),
                "StudentPlatform",
                "Grades approved for " + materie + "\n");
    }

    @PreAuthorize("hasAuthority('profesor')")
    @PutMapping("/addGrade")
    public void addGrade(@RequestParam("grade") int grade,
                         @RequestParam("firstName") String firstName,
                         @RequestParam("lastName") String lastName,
                         @RequestParam("materie") String materie) {

        Materie materie1 = this.classService.getMaterie(materie);
        Student student = this.userService.getStudent(firstName, lastName);

        System.out.println(student.getId_user() + " " + student.getRole());
        Grade grade1 = new Grade();
        grade1.setGrade(grade);
        grade1.setApprovedGrade(false);
        grade1.setMaterie(materie1);
        grade1.setStudent(student);

        this.gradesService.addGrades(grade1);
    }

//    @PreAuthorize("hasAuthority('profesor')")
//    @PutMapping()
}

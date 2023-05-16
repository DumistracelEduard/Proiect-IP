package ip.studentplatform.controller;

import ip.studentplatform.entity.*;
import ip.studentplatform.service.ClassService;
import ip.studentplatform.service.EmailSenderService;
import ip.studentplatform.service.GradesService;
import ip.studentplatform.service.UserService;
import jakarta.persistence.MappedSuperclass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.security.Principal;
import java.util.List;

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

        Grade grade1 = new Grade();
        grade1.setGrade(grade);
        grade1.setApprovedGrade(false);
        grade1.setMaterie(materie1);
        grade1.setStudent(student);

        this.gradesService.addGrades(grade1);
    }

    @PreAuthorize("hasAuthority('profesor')")
    @PutMapping("/addGradeMultiple")
    public void addGradeMultiple(@RequestParam("grade") int grade,
                         @RequestParam("firstName") List<String> firstName,
                         @RequestParam("lastName") List<String> lastName,
                         @RequestParam("materie") String materie) {

        Materie materie1 = this.classService.getMaterie(materie);
        for(int i = 0; i < firstName.size(); ++i) {
            Student student = this.userService.getStudent(firstName.get(i), lastName.get(i));

            Grade grade1 = new Grade();
            grade1.setGrade(grade);
            grade1.setApprovedGrade(false);
            grade1.setMaterie(materie1);
            grade1.setStudent(student);

            this.gradesService.addGrades(grade1);
        }

    }

    @PreAuthorize("hasAuthority('profesor')")
    @PutMapping("/updateGrade")
    public void updateGrade(@RequestParam("grade") int grade,
                            @RequestParam(name = "firstName") String firstName,
                            @RequestParam(name = "lastName") String lastName,
                            @RequestParam(name = "nameMaterie") String nameMaterie) {
        Materie materie1 = this.classService.getMaterie(nameMaterie);
        Student student = this.userService.getStudent(firstName, lastName);
        this.gradesService.updateGrade(grade, student, materie1);
        List<Admin> adminList = this.userService.getListAdmin();
        for(Admin admin: adminList) {
            senderService.sendSimpleEmail(admin.getEmail(),
                    "Update Grades",
                    "Student " + student.getFirstName() + " " + student.getLastName() + " update grade " + nameMaterie);
        }
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/getGradesForAdmin")
    public List<Grade> getGradesForAdmin(@RequestParam("firstName") String firstName,
                                         @RequestParam("lastName") String lastName) {
        Student student = this.userService.getStudent(firstName, lastName);
        return this.gradesService.getGradeForAdmin(student);
    }


}

package ip.studentplatform.controller;

import ip.studentplatform.entity.User;
import ip.studentplatform.service.EmailSenderService;
import ip.studentplatform.service.GradesService;
import ip.studentplatform.service.UserService;
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
}

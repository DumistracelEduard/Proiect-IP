package ip.studentplatform.controller;

import ip.studentplatform.entity.Materie;
import ip.studentplatform.service.ClassService;
import ip.studentplatform.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/class")
public class ClassController {
    @Autowired
    ClassService classService;

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
        materies.add(this.classService.getMaterie(nameMaterie));
        this.classService.addStudentClass(name, materies);
    }
}

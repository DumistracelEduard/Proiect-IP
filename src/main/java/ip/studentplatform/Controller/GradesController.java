package ip.studentplatform.Controller;

import ip.studentplatform.Service.GradesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Grades")
public class GradesController {
    @Autowired
    GradesService gradesService;
}

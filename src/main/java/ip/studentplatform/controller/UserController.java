package ip.studentplatform.controller;

import ip.studentplatform.entity.Professor;
import ip.studentplatform.entity.Student;
import ip.studentplatform.entity.User;
import ip.studentplatform.service.EmailSenderService;
import ip.studentplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    private EmailSenderService senderService;
    @PreAuthorize("hasAuthority('admin')")
    @PostMapping("/upload-customers-data")
    public ResponseEntity<?> uploadCustomersData(@RequestParam("file") MultipartFile file){
        List<User> users = this.userService.saveCustomersToDatabase(file);
        for(User user:users) {
            senderService.sendSimpleEmail(user.getEmail(),
                    "StudentPlatform",
                    "USER: "+ user.getUsername() +"\nPassword:"+user.getInitialPassword());
        }
        return ResponseEntity
                .ok(Map.of("Message" , " Customers data uploaded and saved to database successfully"));
    }

    @GetMapping("/getUser")
    public User getUserByUsername(@RequestParam(name = "name") String name) {
        return this.userService.getUser(name);
    }

//    @GetMapping("/login")
//    public ModelAndView login() {
//        ModelAndView model = new ModelAndView("login.html");
//        return model;
//    }
//
//    @GetMapping("/successful")
//    public ModelAndView successful() {
//        ModelAndView model = new ModelAndView("login_success.html");
//        return model;
//    }

    @GetMapping("/getListStudents")
    public List<Student> getStudentsList() {
        return this.userService.getStudentList();
    }

    @GetMapping("/addBirthday")
    public int addBirthday(@RequestParam(name = "birthday") String birthday, Principal principal) {
        Student student = (Student) this.userService.getUser(principal.getName());
        return this.userService.addBirthday(birthday, student.getFirstName(), student.getLastName());
    }

    @GetMapping("/addCnp")
    public int addCnp(@RequestParam(name = "cnp") String cnp, Principal principal) {
        Student student = (Student) this.userService.getUser(principal.getName());
        return this.userService.addCnp(cnp, student.getFirstName(), student.getLastName());
    }

    @GetMapping("/phoneNumber")
    public int addPhoneNumber(@RequestParam(name = "phoneNumber") String phoneNumber, Principal principal) {
        Student student = (Student) this.userService.getUser(principal.getName());
        return this.userService.addPhoneNumber(phoneNumber, student.getFirstName(), student.getLastName());
    }

    @GetMapping("/dorm")
    public int addDorm(@RequestParam(name = "dorm") String dorm, Principal principal) {
        Student student = (Student) this.userService.getUser(principal.getName());
        return this.userService.addDorm(dorm, student.getFirstName(), student.getLastName());
    }

    @GetMapping("/room")
    public int addCamin(@RequestParam(name = "room") String room, Principal principal) {
        Student student = (Student) this.userService.getUser(principal.getName());
        return this.userService.addRoom(room, student.getFirstName(), student.getLastName());
    }

    @GetMapping("/highSchool")
    public int addHighSchool(@RequestParam(name = "highSchool") String highSchool, Principal principal) {
        Student student = (Student) this.userService.getUser(principal.getName());
        return this.userService.addHighSchool(highSchool, student.getFirstName(), student.getLastName());
    }

    @GetMapping("/iban")
    public int addIban(@RequestParam(name = "iban") String iban, Principal principal) {
        Student student = (Student) this.userService.getUser(principal.getName());
        return this.userService.addIban(iban, student.getFirstName(), student.getLastName());
    }
    @PostMapping("/putData")
    public void updateData(@RequestParam(name = "highSchool") String highSchool,
                      @RequestParam(name = "room") String room,
                      @RequestParam(name = "dorm") String dorm,
                      @RequestParam(name = "phoneNumber") String phoneNumber,
                      @RequestParam(name = "cnp") String cnp,
                      @RequestParam(name = "birthday") String birthday,
                      @RequestParam(name = "iban") String iban,
                      Principal principal) {
        Student student = (Student) this.userService.getUser(principal.getName());
        this.userService.addIban(iban, student.getFirstName(), student.getLastName());
        this.userService.addHighSchool(highSchool, student.getFirstName(), student.getLastName());
        this.userService.addRoom(room, student.getFirstName(), student.getLastName());
        this.userService.addDorm(dorm, student.getFirstName(), student.getLastName());
        this.userService.addPhoneNumber(phoneNumber, student.getFirstName(), student.getLastName());
        this.userService.addCnp(cnp, student.getFirstName(), student.getLastName());
        this.userService.addBirthday(birthday, student.getFirstName(), student.getLastName());
    }
}

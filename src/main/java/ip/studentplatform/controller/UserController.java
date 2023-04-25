package ip.studentplatform.controller;

import ip.studentplatform.entity.Professor;
import ip.studentplatform.entity.User;
import ip.studentplatform.service.EmailSenderService;
import ip.studentplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

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

}

package ip.studentplatform.service;

import ip.studentplatform.entity.Professor;
import ip.studentplatform.repository.ICrudRepositoryUser;
import ip.studentplatform.entity.MyUserDetails;
import ip.studentplatform.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    ICrudRepositoryUser iCrudRepositoryUser;

    public List<User> saveCustomersToDatabase(MultipartFile file) {
        ExcelUploadService excelUploadService = new ExcelUploadService();

        if (ExcelUploadService.isValidExcelFile(file)) {
            try {
                excelUploadService.getCustomersDataFromExcel(file.getInputStream());
                this.iCrudRepositoryUser.saveAll(excelUploadService.usersList);

            } catch (IOException e) {
                throw new IllegalArgumentException("The file is not a valid excel file");
            }
        }
        return excelUploadService.usersList;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = iCrudRepositoryUser.getStudentByUsername(username);
        if (user == null) {
            user = iCrudRepositoryUser.getProfessorByUsername(username);
        }
        if (user == null) {
            user = iCrudRepositoryUser.getAdminByUsername(username);
        }
        if (user == null) {
            throw new UsernameNotFoundException("Not found User");
        }

        return new MyUserDetails(user);
    }

    public List<Professor> getAll() {
        return iCrudRepositoryUser.getAll();
    }
}

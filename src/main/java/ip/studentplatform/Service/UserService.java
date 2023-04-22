package ip.studentplatform.Service;

import ip.studentplatform.Repository.ICrudRepositoryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UserService {
    @Autowired
    ICrudRepositoryUser iCrudRepositoryUser;
    public void saveCustomersToDatabase(MultipartFile file){
        ExcelUploadService excelUploadService = new ExcelUploadService();

        if(ExcelUploadService.isValidExcelFile(file)){
            try {
                excelUploadService.getCustomersDataFromExcel(file.getInputStream());
                this.iCrudRepositoryUser.saveAll(excelUploadService.usersList);

            } catch (IOException e) {
                throw new IllegalArgumentException("The file is not a valid excel file");
            }
        }
    }
}

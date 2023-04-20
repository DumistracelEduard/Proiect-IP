package ip.studentplatform.Service;

import ip.studentplatform.Repository.ICrudRepositoryGrades;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GradesService {
    @Autowired
    ICrudRepositoryGrades iCrudRepositoryGrades;
}

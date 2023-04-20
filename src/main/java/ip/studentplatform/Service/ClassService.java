package ip.studentplatform.Service;

import ip.studentplatform.Repository.ICrudRepositoryClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassService {
    @Autowired
    ICrudRepositoryClass iCrudRepositoryClass;
}

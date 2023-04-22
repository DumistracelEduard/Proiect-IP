package ip.studentplatform.service;

import ip.studentplatform.repository.ICrudRepositoryClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassService {
    @Autowired
    ICrudRepositoryClass iCrudRepositoryClass;
}

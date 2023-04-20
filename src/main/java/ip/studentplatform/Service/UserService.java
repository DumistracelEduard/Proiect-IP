package ip.studentplatform.Service;

import ip.studentplatform.Repository.ICrudRepositoryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    ICrudRepositoryUser iCrudRepositoryUser;
}

package ip.studentplatform.Repository;

import ip.studentplatform.entity.Professor;
import ip.studentplatform.entity.Student;
import ip.studentplatform.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ICrudRepositoryUser extends CrudRepository<User, Integer>{
}

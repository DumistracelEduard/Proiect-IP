package ip.studentplatform.Repository;

import ip.studentplatform.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface GradesCrudRepository extends CrudRepository<User, Integer>, ICrudRepositoryGrades {
}

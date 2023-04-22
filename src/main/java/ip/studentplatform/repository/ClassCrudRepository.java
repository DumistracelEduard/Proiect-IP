package ip.studentplatform.repository;

import ip.studentplatform.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface ClassCrudRepository extends CrudRepository<User, Integer>, ICrudRepositoryClass{
}

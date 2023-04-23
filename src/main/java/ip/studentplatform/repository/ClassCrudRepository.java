package ip.studentplatform.repository;

import ip.studentplatform.entity.Materie;
import ip.studentplatform.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Transactional
public interface ClassCrudRepository extends CrudRepository<User, Integer>, ICrudRepositoryClass {
    @Modifying
    @Query("UPDATE Student u SET u.materies =: materieList WHERE u.firstName =:name")
    int addStudentClass(String name, List<Materie> materieList);

    @Query("SELECT c.materies FROM Student c WHERE c.firstName=: name")
    List<Materie> getMaterieByName(String name);

    @Query("SELECT c FROM Materie c WHERE c.nameMaterie=:name")
    Materie getMaterie(String name);
}

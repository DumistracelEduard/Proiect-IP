package ip.studentplatform.repository;

import ip.studentplatform.entity.Materie;
import ip.studentplatform.entity.Professor;
import ip.studentplatform.entity.Student;
import ip.studentplatform.entity.User;
import jakarta.transaction.Transactional;
import org.apache.xmlbeans.impl.xb.xsdschema.Attribute;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Transactional
public interface ICrudRepositoryUser extends CrudRepository<User, Integer>{
    @Query("SELECT c FROM Student c WHERE c.username like %?1")
    public User getStudentByUsername(@Param("username") String username);

    @Query("SELECT c FROM Professor c WHERE c.username like %?1")
    public User getProfessorByUsername(@Param("username") String username);

    @Query("SELECT c FROM Professor c WHERE c.username=:name")
    public User getProfessorByName(@Param("name") String name);

    @Query("SELECT c FROM Admin c WHERE c.username like %?1")
    public User getAdminByUsername(@Param("username") String username);

    @Query("SELECT s FROM Professor s")
    public List<Professor> getAll();

    @Modifying
    @Query("UPDATE Professor u SET u.materies =:materieList WHERE u.firstName=:name")
    int updateProfessor(String name, List<Materie> materieList);

    @Query("SELECT c.materies FROM Professor c WHERE c.username=:name")
    List<Materie> getMaterieByNameProfessor(String name);

    @Query("SELECT s FROM Student s")
    public List<Student> getAllStudent();

}

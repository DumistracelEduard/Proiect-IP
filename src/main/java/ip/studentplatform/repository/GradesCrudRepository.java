package ip.studentplatform.repository;

import ip.studentplatform.entity.Grade;
import ip.studentplatform.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

@Transactional
public interface GradesCrudRepository extends CrudRepository<Grade, Integer>, ICrudRepositoryGrades {
    @Modifying
    @Query("UPDATE Grade g SET g.approvedGrade = true WHERE g.student = (SELECT s FROM Student s WHERE s.firstName=:firstName and s.lastName=:lastName)" +
            "and g.materie=(SELECT m FROM Materie m where m.nameMaterie=:nameMaterie)")
    void approvedGrades(String firstName, String lastName, String nameMaterie);

    Grade save(Grade grade);
}

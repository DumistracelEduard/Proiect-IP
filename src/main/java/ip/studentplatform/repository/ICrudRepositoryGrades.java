package ip.studentplatform.repository;

import ip.studentplatform.entity.Grade;
import ip.studentplatform.entity.Materie;
import ip.studentplatform.entity.Student;
import org.springframework.data.jpa.repository.Query;

public interface ICrudRepositoryGrades {
    public void approvedGrades(String firstName, String lastName, String materie);
    public Grade save(Grade grade);
    int updateGrade(int grade, Student student, Materie materie);
}

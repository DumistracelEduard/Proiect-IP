package ip.studentplatform.repository;

import ip.studentplatform.entity.Grade;
import org.springframework.data.jpa.repository.Query;

public interface ICrudRepositoryGrades {
    public void approvedGrades(String firstName, String lastName, String materie);

    public Grade save(Grade grade);
}

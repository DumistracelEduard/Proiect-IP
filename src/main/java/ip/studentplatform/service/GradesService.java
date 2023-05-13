package ip.studentplatform.service;

import ip.studentplatform.entity.Grade;
import ip.studentplatform.repository.ICrudRepositoryGrades;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GradesService {
    @Autowired
    ICrudRepositoryGrades iCrudRepositoryGrades;

    public void approvedGrades(String firstName, String lastName, String materie) {
        this.iCrudRepositoryGrades.approvedGrades(firstName, lastName, materie);
    }

    public void addGrades(Grade grade) {
        this.iCrudRepositoryGrades.save(grade);
    }
}

package ip.studentplatform.service;

import ip.studentplatform.entity.Materie;
import ip.studentplatform.repository.ICrudRepositoryClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassService {
    @Autowired
    ICrudRepositoryClass iCrudRepositoryClass;

    public void addStudentClass(String name, List<Materie> materieList) {
        this.iCrudRepositoryClass.addStudentClass(name, materieList);
    }

    public List<Materie> findListMaterie(String name) {
        return this.iCrudRepositoryClass.getMaterieByName(name);
    }

    public Materie getMaterie(String name) {
        return this.iCrudRepositoryClass.getMaterie(name);
    }
}

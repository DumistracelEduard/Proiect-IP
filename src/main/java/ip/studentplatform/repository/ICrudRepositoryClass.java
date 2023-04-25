package ip.studentplatform.repository;

import ip.studentplatform.entity.Materie;
import ip.studentplatform.entity.Professor;

import java.util.List;

public interface ICrudRepositoryClass {
    int addStudentClass(String name, List<Materie> materieList);
    List<Materie> getMaterieByName(String name);
    Materie getMaterie(String name);

    int updateMaterie(int id, Professor professor);
}

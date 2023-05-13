package ip.studentplatform.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@Entity
@Table(name = "grade")
public class Grade {
    @Id
    @GeneratedValue
    @Column(name = "idgrade")
    int idgrade;

    @Column(name = "grade")
    int grade;

    @Column(name = "approved")
    Boolean approvedGrade;

    @Column(name = "flag")
    Boolean flagGrade;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    Student student;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_mat")
    Materie materie;
}

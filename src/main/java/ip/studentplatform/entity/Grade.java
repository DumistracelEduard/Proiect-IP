package ip.studentplatform.entity;

import jakarta.persistence.*;
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
    @Column(name = "idgrade")
    int idgrade;

    @Column(name = "grade")
    int grade;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    Student student;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_mat")
    Materie materie;
}

package ip.studentplatform.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@ToString
@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "student")
public class Student extends User{
    @Column(name = "lastName")
    String lastName;

    @Column(name = "firstName")
    String firstName;

    @Column(name = "address")
    String address;

    @Column(name = "serie")
    String serie;

    @Column(name = "grupa")
    String grupa;

    @Column(name = "phoneNumber")
    String phone;

    @Column(name = "birthDay")
    String birthDay;

    @Column(name = "iban")
    String iban;

    @Column(name = "highSchool")
    String highSchool;

    @Column(name = "dorm")
    String dorm;

    @Column(name = "room")
    String room;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_mat")
    List<Materie> materies;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idgrade")
    List<Grade> grade;

    public Student(int id_user, String password, String username,
                   String lastName, String firstName, String address,
                   String role, String serie, String grupa, String email, String cnp) {
        super(password, username, role, email, cnp);
        this.lastName = lastName;
        this.firstName = firstName;
        this.address = address;
        this.serie = serie;
        this.grupa = grupa;
        this.materies = new ArrayList<>();
        this.grade = new ArrayList<>();
    }
}

package ip.studentplatform.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@Setter
@Getter
@Entity
@Table(name = "student")
public class Student {
    @Id
    @Column(name = "id")
    int id;

    @Column(name = "username")
    String username;

    @Column(name = "password")
    String password;

    @Column(name = "lastName")
    String lastName;

    @Column(name = "firstName")
    String firstName;

    @Column(name = "address")
    String address;

    @Column(name = "role")
    String role;

    @Column(name = "serie")
    String serie;

    @Column(name = "grupa")
    String grupa;

    @Column(name = "email")
    String email;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_mat")
    List<Materie> materies;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idgrade")
    List<Grade> grade;
}

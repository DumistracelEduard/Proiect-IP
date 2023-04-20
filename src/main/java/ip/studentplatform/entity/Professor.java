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
@Table(name = "professor")
public class Professor {
    @Id
    @Column(name = "id_prof")
    int id_prof;

    @Column(name = "username")
    String username;

    @Column(name = "password")
    String password;

    @Column(name = "lastName")
    String lastName;

    @Column(name = "firstName")
    String firstName;

    @Column(name = "email")
    String email;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_mat")
    List<Materie> materies;
}

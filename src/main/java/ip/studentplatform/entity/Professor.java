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
@Table(name = "professor")
public class Professor extends User{
    @Column(name = "lastName")
    String lastName;

    @Column(name = "firstName")
    String firstName;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_mat")
    List<Materie> materies;

    public Professor(int id_user, String password, String username,
                     String lastName, String firstName, String email, String role) {
        super(password, username, role, email);
        this.lastName = lastName;
        this.firstName = firstName;
        this.materies = new ArrayList<>();
    }
}

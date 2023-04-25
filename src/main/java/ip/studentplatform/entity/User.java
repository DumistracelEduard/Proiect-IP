package ip.studentplatform.entity;

import jakarta.persistence.*;
import lombok.*;

@ToString
@Setter
@Getter
@NoArgsConstructor
@MappedSuperclass
public class User {
    @Id
    @GeneratedValue
    @Column(name = "id_user")
    int id_user;
    @Column(name = "password")
    String password;

    @Column(name = "username", unique = true)
    String username;
    @Column(name = "role")
    String role;

    @Column(name = "email", unique = true)
    String email;

    @Column(name = "initial_password")
    String initialPassword;

    public User(String password, String username, String role, String email) {
        this.password = password;
        this.username = username;
        this.role = role;
        this.email = email;
    }
}

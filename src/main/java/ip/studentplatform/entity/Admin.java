package ip.studentplatform.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @Column(name = "id_admin")
    int id_admin;

    @Column(name =  "username")
    String username;

    @Column(name = "password")
    String password;

    @Column(name = "email")
    String email;
}

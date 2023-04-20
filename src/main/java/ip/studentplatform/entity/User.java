package ip.studentplatform.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Service;

@ToString
@Setter
@Getter
@MappedSuperclass
public class User {
    @Id
    @Column(name = "id_user")
    int id_user;
    @Column(name = "password")
    String password;
    @Column(name = "username")
    String username;
}

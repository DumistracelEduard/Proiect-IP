package ip.studentplatform.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "admin")
public class Admin extends User {
    @Column(name = "email")
    String email;

    public Admin(String password, String username, String email, String role) {
        super(password, username, role);
        this.email = email;
    }
}

package app.manage.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_roles", schema = "manager", catalog = "")
public class UserRoles {
    private String username;
    private String role;


    @Id
    @Column(name = "username", nullable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @Basic
    @Column(name = "role", nullable = false, length = 45)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public UserRoles() {
    }

    public UserRoles(String username, String role) {
        this.username = username;
        this.role = role;
    }
}

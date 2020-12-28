package edu.bada.samochodex.security.auth;

import edu.bada.samochodex.security.ApplicationUserRole;

import javax.persistence.*;

@Entity
@Table(name = "application_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id", unique = true)
    private int id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "user_password", nullable = false)
    private String password;

    @Column(name = "user_role")
    private String role = ApplicationUserRole.CLIENT.name();

    @Column(name = "enabled")
    private boolean isEnabled = true;

    public User(String username, String password, String role, boolean enabled) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.isEnabled = enabled;
    }

    public User() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }
}

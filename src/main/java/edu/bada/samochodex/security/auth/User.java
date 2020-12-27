package edu.bada.samochodex.security.auth;

import javax.persistence.*;

@Entity
@Table(name = "application_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int user_id;
    private String username;
    private String user_password;
    private String user_role;
    private boolean enabled;

    public User(String username, String user_password, String user_role, boolean enabled) {
        this.username = username;
        this.user_password = user_password;
        this.user_role = user_role;
        this.enabled = enabled;
    }

    public User() {}

    public long getId() {
        return user_id;
    }

    public void setId(int id) {
        this.user_id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return user_password;
    }

    public void setPassword(String password) {
        this.user_password = password;
    }

    public String getRole() {
        return user_role;
    }

    public void setRole(String role) {
        this.user_role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}

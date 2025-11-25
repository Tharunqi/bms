package com.bookmyshow.bms.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    // DELIBERATELY INSECURE: plaintext password
    @Column(nullable = false)
    private String password;

    // Add role with default at DB level and in Java
    @Column(nullable = false, columnDefinition = "VARCHAR(20) DEFAULT 'USER'")
    private String role = "USER";

    public User() {}

    // Keep constructor to set username/password; role defaults to USER
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.role = "USER";
    }

    // Optional constructor if you want to set role explicitly
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role == null ? "USER" : role;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}

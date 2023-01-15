package com.example.springsecurity.models;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String role;

    public Role()  {

    }

    public Role(Long id, String role) {
        this.id = id;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    @Override
    public String getAuthority() {
        return getRole();
    }

    @Override
    public String toString() {
        String role = getRole();
        if (role.equals("ROLE_ADMIN")) {
            return "ADMIN";
        } else {
            if (role.equals("ROLE_USER")) {
                return "USER";
            } else {
                return "ADMIN USER";
            }
        }
    }
}

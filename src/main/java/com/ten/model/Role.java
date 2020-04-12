package com.ten.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ten.dto.UserDTO;
import org.springframework.security.core.GrantedAuthority;
import java.util.HashSet;
import java.util.Set;

public class Role implements GrantedAuthority {

    private Long id;

    private String name;

    @JsonIgnore
    private Set<UserDTO> users = new HashSet<>();

    public Role() {
    }

    public Set<UserDTO> getUsers() {//вызывается при edit
        return users;
    }

    public void setUsers(Set<UserDTO> users) {//вызывается при edit
        this.users = users;
    }

    public void setName(String name) {//вызывается при edit
        this.name = name;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Role(String name) {
        this.name = name;
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

    public String getName() {//вызывается при edit
        return name;
    }

    @Override
    public String toString() {
        if (name.contains("ADMIN") && name.contains("USER")) {
            return "admin, user";
        } else if (name.contains("ADMIN")) {
            return "admin";
        }
        return "user";
    }

    @JsonIgnore
    public String getAuthority() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return name.equals(role.name);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + name.hashCode();
        return result;
    }
}

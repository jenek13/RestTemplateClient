package com.ten.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ten.model.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class UserDTO implements UserDetails {

    private Long id;
    private String login;
    private String password;
    private Long role;

    public Long getId() {
        return id;
    }

    private Set<Role> roles = new HashSet<>();

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public String getLogin() {
        return login;
    }

    public Long getRole() {
        return role;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setRole(Long role) {
        this.role = role;
    }

    public Set<Role> getRoles() {//вызывается полсе userDTOService getUserById
        return roles;
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}

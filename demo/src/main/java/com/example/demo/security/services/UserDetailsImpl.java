package com.example.demo.security.services;

import com.example.demo.model.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private String id;
    private String username;
    private String email;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(String id, String username, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(Student user) {
        // Map the roles of the user to GrantedAuthority
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name())) // Convert each role to SimpleGrantedAuthority
                .collect(Collectors.toList()); // Collect into a list

        // Return a new UserDetailsImpl object
        return new UserDetailsImpl(
                user.getId(), // User ID
                user.getUsername(), // Username
                user.getEmail(), // Email
                user.getPassword(), // Password
                authorities); // User authorities
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Account is not expired
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Account is not locked
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Credentials are not expired
    }

    @Override
    public boolean isEnabled() {
        return true; // Account is enabled
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) // Check if the same object
            return true;
        if (o == null || getClass() != o.getClass()) // Check if the object is null or not of the same class
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o; // Cast to UserDetailsImpl
        return Objects.equals(id, user.id); // Check if IDs are equal
    }
}

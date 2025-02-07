package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;

/** User object model that will represent the data that will contain the information for the users that will access the web platform, likewise,
 *  it will serve to model the users in the database. Contain */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection= "users")
public class Student implements UserDetails {

    @Id
    private String id;

    @Indexed(unique = true)
    @JsonProperty("name")
    @NonNull
    private String name;

    /** this variable contain date of the birthday of user*/
    @JsonProperty("dob")
    private LocalDate dob;

    @Indexed(unique = true)
    @JsonProperty("email")
    @Email
    @NonNull
    private String email;

    @JsonIgnore
    @JsonProperty("password")
    @NonNull
    private String password;

    @Transient
    private int age;

    public Student(String username, String email, String encode) {
        this.name = username;
        this.email = email;
        this.password = encode;
    }

    /** calculate age of user as of date of birthday */
    public int getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    @DBRef
    private Set<Role> roles = new HashSet<>();

    public Student(String name, LocalDate dob, String email, String password){
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.password = password;
    }

    public @NonNull String getName() { return name; }

    public void setName(@NonNull String name) { this.name = name; }

    public String getEmail() { return email; }

    public void setEmail(@NonNull String email) { this.email = email; }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    public @NonNull String getPassword() { return password; }

    @Override
    public String getUsername() {
        return "";
    }

    public void setPassword(@NonNull String password) { this.password = password; }

    public Set<Role> getRoles() { return roles; }

    public void setRoles(Set<Role> roles) { this.roles = roles; }


}
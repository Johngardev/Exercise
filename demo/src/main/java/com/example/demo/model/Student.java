package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collection;
import java.util.Collections;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection= "student")
public class Student implements UserDetails {

    @Id
    private String id;

    @Indexed(unique = true)
    @JsonProperty("name")
    @NonNull
    private String name;

    @JsonProperty("dob")
    private LocalDate dob;

    @Indexed(unique = true)
    @JsonProperty("email")
    @NonNull
    private String email;

    @JsonIgnore
    @JsonProperty("password")
    @NonNull
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return Collections.EMPTY_LIST;
    }

    @Transient
    private int age;

    public int getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public Student(String id, String name, LocalDate dob, String email, String password){
        super();
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.password = password;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired(){
        return true;
    }

    @Override
    public boolean isAccountNonLocked(){
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }

    public boolean isEnable(){
        return true;
    }


}
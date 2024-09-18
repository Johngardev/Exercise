package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.Period;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection= "student")
public class Student {

    @Id
    private ObjectId id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("dob")
    private LocalDate dob;

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    @Transient
    private int age;

    public int getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public Student(ObjectId id, String name, LocalDate dob, String email, String password){
        super();
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.password = password;
    }

}
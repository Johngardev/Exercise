package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.Period;

@Data
@AllArgsConstructor
@Document(collection= "student")
public class Student {

    public Student(){
    }
    private Object id;
    private String name;
    private LocalDate dob;

    @Transient
    private int age;

    public int getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

}

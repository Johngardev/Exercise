package com.example.demo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document
@Data
public class RefreshToken {
    @Id
    String id;
    @DocumentReference(lazy = true)
    private Student owner;

    public RefreshToken(String id, Student owner) {
        this.id = id;
        this.owner = owner;
    }

    public RefreshToken(){

    }
}

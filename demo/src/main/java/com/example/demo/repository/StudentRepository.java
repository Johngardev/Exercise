package com.example.demo.repository;

import com.example.demo.model.Student;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StudentRepository  extends MongoRepository<Student,UUID> {

    @Query("{name: '?0'}")
    List<Student> findByName(String name);

    Student deleteById(ObjectId id);

    boolean existsById(ObjectId id);

    Student findById(ObjectId id);
}

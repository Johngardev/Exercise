package com.example.demo.repository;

import com.example.demo.model.Student;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentRepository  extends MongoRepository<Student,UUID> {

    Optional<Student> findByName(String name);

    Student deleteById(ObjectId id);

    boolean existsById(String id);

    Optional<Student> findById(String id);

    boolean existsByEmail(String email);

}

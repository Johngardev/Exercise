package com.example.demo.repository;

import com.example.demo.model.Exam;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface ExamRepository extends MongoRepository<Exam, UUID> {
  Exam deleteById(ObjectId id);

  Exam findById(String id);

  boolean existsById(String id);
}

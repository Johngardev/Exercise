package com.example.demo.repository;

import com.example.demo.model.Student;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for accessing User entities in the MongoDB database.
 * It extends MongoRepository, providing CRUD operations for User objects.
 */
@Repository
public interface StudentRepository  extends MongoRepository<Student,UUID> {

    /**
     * Find a User by their username.
     *
     * @param name The username of the user.
     * @return An Optional containing the User if found, or empty if not found.
     */
    Optional<Student> findByName(String name);

    /**
     * Delete a User by their ID.
     *
     * @param id The ID of the user.
     * @return An Optional containing the User if found, or empty if not found.
     */
    Student deleteById(ObjectId id);

    /**
     * Find a User by their ID.
     *
     * @param id The ID of the user.
     * @return a value boolean true or false.
     */
    boolean existsById(String id);

    /**
     * Find a User by their ID.
     *
     * @param id The ID of the user.
     * @return An Optional containing the User if found, or empty if not found.
     */
    Optional<Student> findById(String id);

    /**
     * Check if an email already exists in the database.
     *
     * @param email The email to check.
     * @return A Boolean indicating whether the email exists (true) or not (false).
     */
    boolean existsByEmail(String email);

}

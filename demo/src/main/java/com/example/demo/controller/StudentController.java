package com.example.demo.controller;


import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.bson.types.ObjectId;
import org.json.JSONException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;
import java.util.logging.Logger;

/**We define the endpoints for the http requests that will come from the frontend for each of the CRUD operations.*/

@CrossOrigin(origins = "*") // Allow cross-origin requests for all origins
@RestController // Indicate that this class is a REST controller
@RequestMapping("/api") // Base URL for authentication-related endpoints
@AllArgsConstructor
public class StudentController {

    @Autowired
    private final StudentService studentService; // Handles user service CRUD

    /**
     * Add user and return a succesful http response
     *
     * @param student (user) The user object containing atributtes of user in the platform
     * @return A Response Http containing user object and Http response or an error message*/
    @SneakyThrows
    @PostMapping(path="/add")
    public @ResponseBody
    String addStudent(@RequestBody Student student, HttpServletResponse response) throws JsonProcessingException {
        return studentService.save(student,response);
    }

    /**
     * Find all users and return a succesful http response
     *
     * @return A Response Http containing list of users load in database and Http response or an error message*/
    @GetMapping(path ="/findAllStudents",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Student> getAllStudents(HttpServletResponse response){
        return studentService.getAllStudents();
    }

    @SneakyThrows
    @PutMapping(path = "/students/{id}")
    public String updateStudent(@PathVariable("id") ObjectId id,
                                @RequestBody Student student, HttpServletResponse response) throws JsonProcessingException {
        return studentService.updateStudent(id, student, response);
    }

    @SneakyThrows
    @DeleteMapping(path = "/delete/{id}")
    public String deleteStudent(@PathVariable ObjectId id, HttpServletResponse response) {
        return studentService.deleteById(id, response);
    }
}

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

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class StudentController {

    //private static Logger logger = (Logger) LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private final StudentService studentService;

    @SneakyThrows
    @PostMapping(path="/add")
    public @ResponseBody
    String addStudent(@RequestBody Student student, HttpServletResponse response) throws JsonProcessingException {
        return studentService.save(student,response);
    }

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

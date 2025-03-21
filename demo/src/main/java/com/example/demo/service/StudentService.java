package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.bson.types.ObjectId;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/** In this class, which is represented as a service, CRUD (Create, Read, Update & Delete) operations are defined,
 *  which will allow us to manage the users in the interaction with the database & the endpoints that will connect to the frontend.*/
@Service // Indicate that this class is a Service
public class StudentService implements UserDetailsService {

    @Autowired
    private StudentRepository studentRepository;

    public String save(Student student, HttpServletResponse response) throws JsonProcessingException, JSONException {
        JSONObject responseJson = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();

        if (student.getName() == null || ("").equals(student.getAge())) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        student = studentRepository.save(student);
        JSONObject studentJSON = new JSONObject(mapper.writeValueAsString(student));
        studentJSON.put("id", student.getId().toString());
        responseJson.put("saveStudentResponse", studentJSON);

        return responseJson.toString();

    }

    public List<Student> getAllStudents(){
        return studentRepository.fetchAllStudents();
    }

    @Transactional
    public String updateStudent(ObjectId id, Student student, HttpServletResponse response) throws JsonProcessingException, JSONException {
        JSONObject responseJson = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        Student employee1 = studentRepository.deleteById(id);

        boolean exists = studentRepository.existsById(String.valueOf(id));

        if (!exists) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

        if(student.getName()!=null){
            employee1.setName(student.getName());
            employee1.setAge(student.getAge());
            studentRepository.save(employee1);

            JSONObject studentJSON = new JSONObject(mapper.writeValueAsString(employee1));
            studentJSON.put("id", employee1.getId().toString());
            responseJson.put("getUpdateStudentResponse", studentJSON);
        }
        return response.toString();
    }

    public String deleteById(ObjectId id, HttpServletResponse response) throws JSONException {
         JSONObject responseJson = new JSONObject();
         ObjectMapper mapper = new ObjectMapper();
         mapper.findAndRegisterModules();

         boolean exists = studentRepository.existsById(String.valueOf(id));

         if(!exists){
             response.setStatus(HttpServletResponse.SC_NOT_FOUND);
         } else {
             responseJson.put("deleted student with id: ", id);
             studentRepository.deleteById(id);
         }
         return responseJson.toString();
    }

    @Override
    public Student loadUserByUsername(String name) throws UsernameNotFoundException {
        return studentRepository.findByName(name).orElseThrow( () -> new UsernameNotFoundException("Username not found"));
    }

    public Student findById(String id) {
        return studentRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User ID not found"));
    }
}


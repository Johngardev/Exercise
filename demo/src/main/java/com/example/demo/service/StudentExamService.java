package com.example.demo.service;

import com.example.demo.model.Exam;
import com.example.demo.model.Student;
import com.example.demo.repository.ExamRepository;
import com.example.demo.repository.StudentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class StudentExamService {
  @Autowired
  private StudentRepository studentRepository;

  @Autowired
  private ExamRepository examRepository;

  public String addExamToStudent(String studentId, String examId, HttpServletResponse response) throws JSONException, IOException {
    JSONObject responseJson = new JSONObject();
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());

    Optional<Student> studentOptional = studentRepository.findById(studentId);
    Optional<Exam> examOptional = Optional.ofNullable(examRepository.findById(examId));

    if (studentOptional.isPresent() && examOptional.isPresent()) {
      Student student = studentOptional.get();
      student.getExamsId().add(examId);
      studentRepository.save(student);

      JSONObject studentJSON = new JSONObject(mapper.writeValueAsString(student));
      responseJson.put("addExamResponse", studentJSON);
      return responseJson.toString();
    } else {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      if (!studentOptional.isPresent()) {
        responseJson.put("error", "Student not found with ID: " + studentId);
      } else {
        responseJson.put("error", "Exam not found with ID: " + examId);
      }
      return responseJson.toString();
    }
  }

  public String removeExamFromStudent(String studentId, String examId, HttpServletResponse response) throws JSONException, IOException {
    JSONObject responseJson = new JSONObject();
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());

    Optional<Student> studentOptional = studentRepository.findById(studentId);

    if (studentOptional.isPresent()) {
      Student student = studentOptional.get();
      boolean removed = student.getExamsId().remove(examId);

      if (removed) {
        studentRepository.save(student);
        JSONObject studentJSON = new JSONObject(mapper.writeValueAsString(student));
        responseJson.put("removeExamResponse", studentJSON);
        return responseJson.toString();
      } else {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        responseJson.put("error", "Exam with ID: " + examId + " not found in student's exams.");
        return responseJson.toString();
      }
    } else {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      responseJson.put("error", "Student not found with ID: " + studentId);
      return responseJson.toString();
    }
  }

}
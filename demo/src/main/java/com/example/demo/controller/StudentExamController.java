package com.example.demo.controller;

import com.example.demo.service.StudentExamService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/users/{studentId}/exams")
public class StudentExamController {

  @Autowired
  private StudentExamService studentExamService;

  @SneakyThrows
  @PostMapping("/{examId}")
  public String addExamToStudent(@PathVariable String studentId, @PathVariable String examId, HttpServletResponse response) throws JsonProcessingException {
    return studentExamService.addExamToStudent(studentId, examId, response);
  }

  @SneakyThrows
  @DeleteMapping("/{examId}")
  public String removeExamFromStudent(@PathVariable String studentId, @PathVariable String examId, HttpServletResponse response) throws JsonProcessingException {
    return studentExamService.removeExamFromStudent(studentId, examId, response);
  }
}

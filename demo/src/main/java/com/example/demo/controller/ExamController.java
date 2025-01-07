package com.example.demo.controller;

import com.example.demo.model.Exam;
import com.example.demo.service.ExamService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ExamController {
  @Autowired
  private final ExamService examService;

  @SneakyThrows
  @PostMapping(path = "/add")
  public @ResponseBody
  String addExam(@RequestBody Exam exam, HttpServletResponse response) throws JsonProcessingException {
    return examService.saveExam(exam,response);
  }

  @GetMapping(path = "/findAllExams", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Exam> getAllExams(HttpServletResponse response){
    return examService.getAllExams();
  }

  @SneakyThrows
  @PutMapping(path = "/exams/{id}")
  public String updateExam(@PathVariable("id") ObjectId id,
                           @RequestBody Exam exam, HttpServletResponse response) throws JsonProcessingException {
    return examService.updateExam(id, exam, response);
  }

  @SneakyThrows
  @DeleteMapping(path = "/delete/{id}")
  public String deleteStudent(@PathVariable ObjectId id, HttpServletResponse response) {
    return examService.deleteById(id, response);
  }
}

package com.example.demo.controller;

import com.example.demo.model.EmailDTO;
import com.example.demo.model.Exam;
import com.example.demo.repository.ExamRepository;
import com.example.demo.service.ExamService;
import com.example.demo.service.IEmailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/apiExam")
@AllArgsConstructor
public class ExamController {
  @Autowired
  private final ExamService examService;

  @Autowired
  private ExamRepository examRepository;

  @Autowired
  private IEmailService emailService;

  @SneakyThrows
  @PostMapping(path = "/add")
  @PreAuthorize("hasRole('ADMIN')")
  public @ResponseBody
  String addExam(@RequestBody Exam exam, HttpServletResponse response) throws JsonProcessingException {
    return examService.saveExam(exam,response);
  }

  @GetMapping(path = "/findAllExams", produces = MediaType.APPLICATION_JSON_VALUE)
  @PreAuthorize("hasRole('ADMIN')")
  public List<Exam> getAllExams(HttpServletResponse response){
    return examService.getAllExams();
  }

  @SneakyThrows
  @PutMapping(path = "/exams/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public String updateExam(@PathVariable("id") ObjectId id,
                           @RequestBody Exam exam, HttpServletResponse response) throws JsonProcessingException {
    return examService.updateExam(id, exam, response);
  }

  @SneakyThrows
  @DeleteMapping(path = "/delete/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public String deleteStudent(@PathVariable ObjectId id, HttpServletResponse response) {
    return examService.deleteById(id, response);
  }

  @PostMapping("/notify-exam")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<String> assignExam(@RequestBody EmailDTO emailDTO) {

    emailService.sendEmail(emailDTO.getToUser(), emailDTO.getSubject(), emailDTO.getMessage());

    return ResponseEntity.ok("Examen asignado con exito!");
  }
}

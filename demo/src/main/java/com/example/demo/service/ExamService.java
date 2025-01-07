package com.example.demo.service;

import com.example.demo.model.Exam;
import com.example.demo.repository.ExamRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.bson.types.ObjectId;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExamService {
  @Autowired
  private ExamRepository examRepository;

  public String saveExam(Exam exam, HttpServletResponse response) throws JsonProcessingException, JSONException {
    JSONObject responseJson = new JSONObject();
    ObjectMapper mapper = new ObjectMapper();
    mapper.findAndRegisterModules();
    if(exam.getExamName() == null){
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }
    exam = examRepository.save(exam);
    JSONObject examJSON = new JSONObject(mapper.writeValueAsString(exam));
    examJSON.put("id", exam.getId().toString());
    responseJson.put("saveExamResponse", examJSON);
    return responseJson.toString();
  }

  public List<Exam> getAllExams(){
    return  examRepository.findAll();
  }

  @Transactional
  public String updateExam(ObjectId id, Exam exam, HttpServletResponse response) throws JsonProcessingException, JSONException {
    JSONObject responseJson = new JSONObject();
    ObjectMapper mapper = new ObjectMapper();
    mapper.findAndRegisterModules();
    Exam employee1 = examRepository.deleteById(id);

    boolean exists = examRepository.existsById(String.valueOf(id));
    if (!exists) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    }

    if(exam.getExamName()!=null){
      employee1.setExamName(exam.getExamName());
      examRepository.save(employee1);

      JSONObject examJSON = new JSONObject(mapper.writeValueAsString(employee1));
      examJSON.put("id", employee1.getId().toString());
      responseJson.put("getUpdateExamResponse", examJSON);
    }
    return response.toString();
  }

  public String deleteById(ObjectId id, HttpServletResponse response) throws JSONException {
    JSONObject responseJson = new JSONObject();
    ObjectMapper mapper = new ObjectMapper();
    mapper.findAndRegisterModules();

    boolean exists = examRepository.existsById(String.valueOf(id));

    if(!exists){
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    } else {
      responseJson.put("deleted Exam with id: ", id);
      examRepository.deleteById(id);
    }
    return responseJson.toString();
  }
}

package com.example.demo.controller;

import com.example.demo.model.EmailDTO;
import com.example.demo.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1")
public class MailController {

  @Autowired
  private IEmailService emailService;

  @PostMapping("/sendMail")
  public ResponseEntity<?> sendMail(@RequestBody EmailDTO emailDTO) {

    System.out.println("Llega mensaje: " + emailDTO.toString());

    emailService.sendEmail(emailDTO.getToUser(), emailDTO.getSubject(), emailDTO.getMessage());

    Map<String, String> response = new HashMap<>();
    response.put("message", "Email sent successfully");

    return ResponseEntity.ok(response);
  }
}

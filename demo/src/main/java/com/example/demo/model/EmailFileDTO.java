package com.example.demo.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class EmailFileDTO {

  private String[] toUser;
  private String subject;
  private String message;
  MultipartFile file;
}

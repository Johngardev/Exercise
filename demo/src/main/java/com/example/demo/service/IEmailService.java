package com.example.demo.service;

import java.io.File;

public interface IEmailService {

  void sendEmail(String[] toUser, String subject, String message);

  void sendEmailFile(String[] toUser, String subject, String message, File file);
}

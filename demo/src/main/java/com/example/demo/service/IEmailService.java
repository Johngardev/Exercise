package com.example.demo.service;

import com.example.demo.model.EmailDTO;
import jakarta.mail.MessagingException;

public interface IEmailService {

  public void senMail(EmailDTO emailDTO) throws MessagingException;
}

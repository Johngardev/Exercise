package com.example.demo.service;

import com.example.demo.model.EmailDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailServiceImpl implements IEmailService{
  private final JavaMailSender javaMailSender;

  private final TemplateEngine templateEngine;

  public EmailServiceImpl(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
    this.javaMailSender = javaMailSender;
    this.templateEngine = templateEngine;
  }

  @Override
  public void senMail(EmailDTO email) throws MessagingException {
    try {
      MimeMessage message =
              javaMailSender.createMimeMessage();
      MimeMessageHelper helper =
              new MimeMessageHelper(message,
                      true, "UTF-8");
      helper.setTo(email.getRecipient());
      helper.setSubject(email.getSubject());

      Context context = new Context();
      context.setVariable("message", email.getMessage());
      String contentHTML = templateEngine.process("email",
              context);
      helper.setText(contentHTML, true);
      javaMailSender.send(message);
    } catch (Exception e) {
        throw new RuntimeException("Error " +
                "To send email: " +
                e.getMessage(), e);
    }
  }
}

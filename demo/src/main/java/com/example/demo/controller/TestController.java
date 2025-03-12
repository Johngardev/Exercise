package com.example.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600) //Allow cross-origin requests from any origin for 1 hour
@RestController // Indicate that this class is a REST controller
@RequestMapping("/api/test") // Base URL for test-related endpoints
public class TestController {

  /**
   * Public endpoint that can be accessed without any authentication.
   *
   * @return A string message indicating public content.
   */
  @GetMapping("/all")
  public String allAccess() { return "Public content."; }

  /**
   * Endpoint accessible only to users with USER, MODERATOR, or ADMIN roles.
   *
   * @return A string message indicating user content.
   */
  @GetMapping("/user")
  @PreAuthorize("hasRole('USER') or " + "hasRole('ADMIN') or " + "hasRole('TEACHER') or " + "hasRole('STUDENT')" )
  public String userAccess() { return "User Content." ; }

  /**
   * Endpoint accessible only to users with the ADMIN role.
   *
   * @return A string message indicating moderator board content.
   */
  @GetMapping("/admin")
  @PreAuthorize("hasRole('ADMIN')")
  public String adminAccess() { return "Admin Board." ; }

  /**
   * Endpoint accessible only to users with the TEACHER role.
   *
   * @return A string message indicating moderator board content.
   */
  @GetMapping("/teacher")
  @PreAuthorize("hasRole('TEACHER')")
  public String teacherAccess() { return "Teacher Board." ; }

  /**
   * Endpoint accessible only to users with the STUDENT role.
   *
   * @return A string message indicating moderator board content.
   */
  @GetMapping("/student")
  @PreAuthorize("hasRole('STUDENT')")
  public String studentAccess() { return "Student Board." ; }
}

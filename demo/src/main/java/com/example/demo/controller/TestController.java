package com.example.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/all")
    public String allAccess() { return "Public content."; }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or " + "hasRole('ADMIN') or " + "hasRole('TEACHER') or " + "hasRole('STUDENT')" )
    public String userAccess() { return "User Content." ; }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() { return "Admin Board." ; }

    @GetMapping("/teacher")
    @PreAuthorize("hasRole('TEACHER')")
    public String teacherAccess() { return "Teacher Board." ; }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String studentAccess() { return "Student Board." ; }

}

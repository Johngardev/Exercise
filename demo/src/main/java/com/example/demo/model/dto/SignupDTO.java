package com.example.demo.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupDTO {

    @NotBlank
    @Size(min = 3, max = 30)
    private String username;
    @NotBlank
    @Size(max = 60)
    private String email;
    @NotBlank
    @Size(min = 6, max = 60)
    private String password;
}

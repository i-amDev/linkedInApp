package com.project.linkedin.user_service.dto;

import lombok.Data;

@Data
public class SignupRequestDto {

    private String username;

    private String email;

    private String password;
}

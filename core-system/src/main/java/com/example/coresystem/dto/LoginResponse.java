package com.example.coresystem.dto;

import lombok.Data;

@Data
public class LoginResponse {

    private String userId;
    private String jwt;

}

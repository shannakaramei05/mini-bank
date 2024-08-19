package com.example.coresystem.dto;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;


@Data
public class UserRegisterRequest {

    private String usrNm;
    private String userId;
    private String password;
    private String email;
}

package com.example.coresystem.services;

import com.example.coresystem.dto.UserRegisterRequest;
import com.example.coresystem.dto.UserRegisterResponse;

public interface UserServices {

    UserRegisterResponse createUser(UserRegisterRequest request);
}

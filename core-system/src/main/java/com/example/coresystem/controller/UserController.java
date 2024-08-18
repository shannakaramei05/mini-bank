package com.example.coresystem.controller;

import com.example.coresystem.dto.*;
import com.example.coresystem.services.UserServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserServices userServices;

    @PostMapping("/registers")
    public UserRegisterResponse createUser(@RequestBody UserRegisterRequest request) {
        return userServices.createUser(request);
    }


    @PostMapping("/login")
    public LoginResponse doLogin(@RequestBody LoginRequest loginRequest) {
        return null;
    }


    @PostMapping("/request/card")
    public RequestCardResponse requestCard(@RequestBody RequestCard requestCard) {
        return null;
    }
}

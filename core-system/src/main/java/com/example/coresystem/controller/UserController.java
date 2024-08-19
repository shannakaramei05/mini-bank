package com.example.coresystem.controller;

import com.example.coresystem.dto.*;
import com.example.coresystem.exception.AuthenticationException;
import com.example.coresystem.security.CustomUserDetails;
import com.example.coresystem.security.JwtUtils;
import com.example.coresystem.security.UserInfo;
import com.example.coresystem.security.UserInfoServices;
import com.example.coresystem.services.UserServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


@RestController
@RequestMapping("/v1/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserServices userServices;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserInfoServices userInfoServices;

    @Autowired
    private JwtUtils jwtUtils;


    @PostMapping("/registers")
    public UserRegisterResponse createUser(@RequestBody UserRegisterRequest request) {
        return userServices.createUser(request);
    }


    @PostMapping("/login")
    @ResponseBody
    public LoginResponse doLogin(@RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = new LoginResponse();
        authenticate(loginRequest.getUserId(), loginRequest.getPassword());
        final CustomUserDetails userDetails = userInfoServices.loadUserByUsername(loginRequest.getUserId());
        UserInfo user = userDetails.getUserInfo();
        logger.info("{}", user);
        String token = jwtUtils.generateTokenFromUsername(userDetails);
        loginResponse.setUserId(user.getUserId());
        loginResponse.setJwt(token);
        return loginResponse;
    }

    @PostMapping("/card-request")
    public RequestCardResponse requestCard(@RequestBody RequestCard requestCard) {
        //update userTable
        //update userActivity
        //insert Accounts Table
        return null;
    }

    private void authenticate(String username, String password) {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (BadCredentialsException e) {
            throw new AuthenticationException("INVALID CREDENTIALS", e);
        }
    }
}

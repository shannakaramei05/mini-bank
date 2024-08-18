package com.example.coresystem.services.serviceImp;

import com.example.coresystem.dto.UserRegisterRequest;
import com.example.coresystem.dto.UserRegisterResponse;
import com.example.coresystem.model.Users;
import com.example.coresystem.repository.UserActivityRepository;
import com.example.coresystem.repository.UserRepository;
import com.example.coresystem.services.UserActivityServices;
import com.example.coresystem.services.UserServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    private  UserRepository userRepository;

    @Autowired
    private  UserActivityServices userActivityServices;

    @Autowired
    private  PasswordEncoder passwordEncoder;

    @Override
    public UserRegisterResponse createUser(UserRegisterRequest request) {

        UserRegisterResponse userRegisterResponse = new UserRegisterResponse();
        Optional<Users> currentUser = userRepository.findByUserId(request.getUserId());

        if(currentUser.isPresent()) {
            userRegisterResponse.setUserId(request.getUserId());
            userRegisterResponse.setMsg("User Id Already Exist");
            return userRegisterResponse;
        }
        Users newUser = new Users();
        newUser.setUserId(request.getUserId());
        newUser.setUsrNm(request.getUsrNm());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setAdmin(Boolean.FALSE);
        newUser.setEmail(request.getEmail());
        newUser.setSysRegDtm(LocalDateTime.now());
        newUser.setSysUpdDtm(LocalDateTime.now());
        newUser.setUsrRegId("00000000");
        newUser.setUsrRegId("00000000");

        userRepository.save(newUser);
        userActivityServices.createUserBase(newUser);


        userRegisterResponse.setUserId(request.getUserId());
        userRegisterResponse.setMsg("new Account already Created. please go to Login Menu.!");
        return userRegisterResponse;
    }
}

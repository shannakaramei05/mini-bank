package com.example.coresystem.services.serviceImp;

import com.example.coresystem.model.UserActivities;
import com.example.coresystem.model.Users;
import com.example.coresystem.repository.UserActivityRepository;
import com.example.coresystem.services.UserActivityServices;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserActivityServicesImpl implements UserActivityServices {

    @Autowired
    private UserActivityRepository userActivityRepository;

    @Override
    public void createUserBase(Users newUser) {

        UserActivities userJourney = new UserActivities();
        userJourney.setUser(newUser);
        userJourney.setChgPw(Boolean.FALSE);
        userJourney.setDebitErrCnt(0);
        userJourney.setLgnErrCnt(0);
        userJourney.setSysRegDtm(LocalDateTime.now());
        userJourney.setSysUpdDtm(LocalDateTime.now());
        userJourney.setUsrRegId("00000000");
        userJourney.setUsrUpdId("00000000");

        userActivityRepository.save(userJourney);

    }
}

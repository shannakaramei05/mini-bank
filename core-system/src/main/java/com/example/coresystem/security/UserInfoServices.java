package com.example.coresystem.security;

import com.example.coresystem.model.Users;
import com.example.coresystem.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoServices implements UserDetailsService {

    private UserRepository userRepository;

    public UserInfoServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public CustomUserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        Optional<Users> user = userRepository.findByUserId(userId);
        if(!user.isPresent()) {
            throw new UsernameNotFoundException("User or Password Wrong");
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(user.get().getUserId());
        userInfo.setPassword(user.get().getPassword());
        userInfo.setUserSts(user.get().getStatus());
        userInfo.setUsrNm(user.get().getUsrNm());

        return new CustomUserDetails(userInfo);
    }
}

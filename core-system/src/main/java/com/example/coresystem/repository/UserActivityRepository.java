package com.example.coresystem.repository;

import com.example.coresystem.model.UserActivities;
import com.example.coresystem.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserActivityRepository extends JpaRepository<UserActivities, Long> {

    Optional<Users> findByUserUserId(String userId);
}

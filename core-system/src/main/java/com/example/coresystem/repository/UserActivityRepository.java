package com.example.coresystem.repository;

import com.example.coresystem.model.UserActivities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserActivityRepository extends JpaRepository<UserActivities, Long> {
}

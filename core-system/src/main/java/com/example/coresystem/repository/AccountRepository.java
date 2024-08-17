package com.example.coresystem.repository;

import com.example.coresystem.model.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepository extends JpaRepository<Accounts, Long> {
}

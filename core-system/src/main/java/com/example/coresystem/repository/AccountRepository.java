package com.example.coresystem.repository;

import com.example.coresystem.model.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AccountRepository extends JpaRepository<Accounts, String> {

    Optional<Accounts> findByCardNo(String cardNo);

    Optional<Accounts> findByAcctNo(String acctNo);
}

package com.example.coresystem.repository;

import com.example.coresystem.model.TransactionHistories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionHistoryRepository extends JpaRepository<TransactionHistories, Long> {
}

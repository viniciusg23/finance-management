package com.finapp.financemanagement.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finapp.financemanagement.domain.model.Transaction;

@Repository
public interface ITransactionRepository extends JpaRepository<Transaction, UUID>{
    
}

package com.anhnt.payment.repository;

import com.anhnt.payment.repository.entity.TransactionEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    TransactionEntity findFirstByPayeeIdOrderByIdAsc(Long payeeId);

}

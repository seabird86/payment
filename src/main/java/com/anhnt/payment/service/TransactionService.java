package com.anhnt.payment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anhnt.common.domain.payment.constant.TransactionStatus;
import com.anhnt.common.domain.payment.request.TransactionCreateRequest;
import com.anhnt.payment.mapper.TransactionMapper;
import com.anhnt.payment.repository.TransactionRepository;
import com.anhnt.payment.repository.entity.TransactionEntity;

@Service
public class TransactionService {

  @Autowired
  private TransactionRepository transactionRepository;
  @Autowired
  private TransactionMapper transactionMapper;

  public TransactionEntity tryCreateBalance(TransactionCreateRequest request) {
    TransactionEntity entity = transactionMapper.toTransactionEntity(request);
    entity.setStatus(TransactionStatus.PENDING);
    return transactionRepository.save(entity);
  }


  public TransactionEntity confirmCreateBalance(TransactionEntity entity) {
    entity.setStatus(TransactionStatus.SUCCESS);
    return transactionRepository.save(entity);
  }

  public TransactionEntity cancelCreateBalance(TransactionEntity entity) {
    entity.setStatus(TransactionStatus.CANCELLED);
    return transactionRepository.save(entity);
  }

  public TransactionEntity createOrder(TransactionCreateRequest request) {
    TransactionEntity entity = transactionMapper.toTransactionEntity(request);
    entity.setStatus(TransactionStatus.PENDING);
    return transactionRepository.save(entity);
  }


  public TransactionEntity confirmCreateTransaction(TransactionEntity entity) {
    entity.setStatus(TransactionStatus.SUCCESS);
    return transactionRepository.save(entity);
  }

  public TransactionEntity cancelCreateTransaction(TransactionEntity entity) {
    entity.setStatus(TransactionStatus.CANCELLED);
    return transactionRepository.save(entity);
  }
}

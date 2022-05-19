package com.anhnt.payment.service;

import com.anhnt.common.domain.payment.constant.TransactionStatus;
import com.anhnt.common.domain.payment.request.TransactionCreateRequest;
import com.anhnt.payment.mapper.TransactionMapper;
import com.anhnt.payment.repository.TransactionRepository;
import com.anhnt.payment.repository.entity.TransactionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionService {

  @Autowired
  private TransactionRepository transactionRepository;

  @Autowired
  private TransactionMapper transactionMapper;


  @Transactional
  public TransactionEntity createOrder(TransactionCreateRequest request) {
    TransactionEntity entity = transactionMapper.toTransactionEntity(request);
    entity.setStatus(TransactionStatus.PENDING);
    entity = transactionRepository.save(entity);
    return entity;
  }
}

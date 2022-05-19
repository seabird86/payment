package com.anhnt.payment.controller;

import com.anhnt.payment.controller.request.TransactionCreateRequest;
import com.anhnt.payment.repository.TransactionRepository;
import com.anhnt.payment.repository.entity.TransactionEntity;
import com.anhnt.payment.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

  @Autowired
  private TransactionService transactionService;
  @Autowired
  private TransactionRepository transactionRepository;

  @PostMapping(value = "/transactions")
  public Long createTransaction(@RequestBody TransactionCreateRequest createOrderRequest) {
    TransactionEntity order = transactionService.createOrder(createOrderRequest);
    return order.getId();
  }
}

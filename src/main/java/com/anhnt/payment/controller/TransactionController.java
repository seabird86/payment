package com.anhnt.payment.controller;

import com.anhnt.common.domain.payment.request.TransactionCreateParam;
import com.anhnt.common.domain.payment.request.TransactionCreateRequest;
import com.anhnt.payment.repository.entity.TransactionEntity;
import com.anhnt.payment.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TransactionController {

  @Autowired
  private TransactionService transactionService;

  @PostMapping(value = "/transactions")
  public Long createTransaction(@SpringQueryMap TransactionCreateParam param, @RequestBody TransactionCreateRequest request) { // ParameterObject
    log.info("=== params ", param);
    TransactionEntity order = transactionService.createOrder(request);
    return order.getId();
  }
}

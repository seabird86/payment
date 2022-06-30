package com.anhnt.payment.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anhnt.common.domain.payment.request.TransactionCreateParam;
import com.anhnt.common.domain.payment.request.TransactionCreateRequest;
import com.anhnt.common.domain.payment.response.CancelCreateTransactionResponse;
import com.anhnt.common.domain.payment.response.ConfirmCreateTransactionResponse;
import com.anhnt.common.domain.payment.response.TryCreateTransactionResponse;
import com.anhnt.common.domain.response.BodyEntity;
import com.anhnt.common.domain.response.ErrorFactory;
import com.anhnt.common.domain.response.ResponseFactory;
import com.anhnt.payment.repository.TransactionRepository;
import com.anhnt.payment.repository.entity.TransactionEntity;
import com.anhnt.payment.service.TransactionService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class TransactionController {

  @Autowired
  private TransactionService transactionService;

  @Autowired
  private TransactionRepository transactionRepository;
  

  @PostMapping(value = "/customers/{userId}/first-transaction")
  public ResponseEntity<BodyEntity<TryCreateTransactionResponse>> tryCreateBalance(@RequestParam Long userId, @SpringQueryMap TransactionCreateParam param, @RequestBody TransactionCreateRequest request) { // ParameterObject
    log.info("=== params {} ", param);
    if (request.getPayeeId()==null){
      return ErrorFactory.PaymentError.INVALID_PAYEE.apply(null).toResponseEntity();
    }
    if (request.getAmount().compareTo(new BigDecimal(100)) < 0){
      return ErrorFactory.PaymentError.BALANCE_AMOUNT_MUST_GREATER_THAN.apply(List.of(new BigDecimal(100))).toResponseEntity();
    }
    TransactionEntity txn = transactionService.tryCreateBalance(request);
    return ResponseFactory.success(new TryCreateTransactionResponse(txn.getId()));
  }

  @PutMapping(value = "/customers/{userId}/first-transaction", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<BodyEntity<ConfirmCreateTransactionResponse>> confirmCreateBalance(@RequestParam Long userId){
    log.info("=== userId {} ", userId);
    TransactionEntity txn = transactionRepository.findFirstByPayeeIdOrderByIdAsc(userId);
    txn = transactionService.confirmCreateBalance(txn);
    return ResponseFactory.success(new ConfirmCreateTransactionResponse(txn.getId()));
  }

  @DeleteMapping(value = "/customers/{userId}/first-transaction", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<BodyEntity<CancelCreateTransactionResponse>> cancelCreateBalance(@RequestParam Long userId){
    log.info("=== userId {} ", userId);
    TransactionEntity txn = transactionRepository.findFirstByPayeeIdOrderByIdAsc(userId);
    if (txn==null){
      return ResponseFactory.success(new CancelCreateTransactionResponse());
    }
    txn = transactionService.cancelCreateBalance(txn);
    return ResponseFactory.success(new CancelCreateTransactionResponse(txn.getId()));
  }


  @PostMapping(value = "/transactions")
  public ResponseEntity<BodyEntity<TryCreateTransactionResponse>> createTransaction(@SpringQueryMap TransactionCreateParam param, @RequestBody TransactionCreateRequest request) { // ParameterObject
    log.info("=== params {} ", param);
    if (request.getPayeeId()==null){
      return ErrorFactory.PaymentError.INVALID_PAYEE.apply(null).toResponseEntity();
    }
    TransactionEntity txn = transactionService.createOrder(request);
    return ResponseFactory.success(new TryCreateTransactionResponse(txn.getId()));
  }

  @PutMapping(value = "/customers/{userId}/transactions/{id}/confirm", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<BodyEntity<ConfirmCreateTransactionResponse>> confirmCreateTransaction(@RequestParam Long id){
    log.info("=== id {} ", id);
    TransactionEntity entity = transactionRepository.getById(id);
    TransactionEntity txn = transactionService.confirmCreateTransaction(entity);
    return ResponseFactory.success(new ConfirmCreateTransactionResponse(txn.getId()));
  }

  @DeleteMapping(value = "/transactions/{id}/cancel", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<BodyEntity<CancelCreateTransactionResponse>> cancelCreateTransaction(@RequestParam Long id){
    log.info("=== id {} ", id);
    Optional<TransactionEntity> optional = transactionRepository.findById(id);
    if (optional.isEmpty()){
      return ResponseFactory.success(new CancelCreateTransactionResponse());
    }
    TransactionEntity txn = transactionService.cancelCreateTransaction(optional.get());
    return ResponseFactory.success(new CancelCreateTransactionResponse(txn.getId()));
  }
}

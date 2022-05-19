package com.anhnt.payment.mapper;

import com.anhnt.payment.controller.request.TransactionCreateRequest;
import com.anhnt.payment.repository.entity.TransactionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    TransactionEntity toTransactionEntity(TransactionCreateRequest request);
}

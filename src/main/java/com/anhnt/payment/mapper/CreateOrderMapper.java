package com.anhnt.payment.mapper;

import com.anhnt.common.domain.payment.event.OrderCreatedEvent;
import com.anhnt.payment.repository.entity.OrderEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CreateOrderMapper{
    OrderCreatedEvent toOrderCreatedEvent(OrderEntity entity);
}

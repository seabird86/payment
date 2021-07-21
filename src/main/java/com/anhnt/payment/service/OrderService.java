package com.anhnt.payment.service;

import com.anhnt.common.domain.payment.constant.OrderState;
import com.anhnt.payment.controller.request.CreateOrderRequest;
import com.anhnt.payment.mapper.CreateOrderMapper;
import com.anhnt.payment.repository.OrderRepository;
import com.anhnt.payment.repository.entity.OrderEntity;
import io.eventuate.tram.events.publisher.DomainEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

  @Autowired
  private DomainEventPublisher domainEventPublisher;

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private CreateOrderMapper createOrderMapper;


  @Transactional
  public OrderEntity createOrder(CreateOrderRequest order) {
    OrderEntity entity = new OrderEntity();
    entity.setState(OrderState.PENDING);
    entity.setAmount(order.getAmount());
    entity.setCustomerId(order.getCustomerId());
    entity = orderRepository.save(entity);
    domainEventPublisher.publish(OrderEntity.class, entity.getId(), List.of(createOrderMapper.toOrderCreatedEvent(entity)));
    return entity;
  }

//  public void approveOrderEntity(Long orderId) {
//    OrderEntity order = orderRepository
//            .findById(orderId)
//            .orElseThrow(() -> new IllegalArgumentException(String.format("order with id %s not found", orderId)));
//    order.noteCreditReserved();
//    domainEventPublisher.publish(OrderEntity.class,
//            orderId, singletonList(new OrderEntityApprovedEvent(order.getOrderEntityDetails())));
//  }
//
//  public void rejectOrderEntity(Long orderId) {
//    OrderEntity order = orderRepository
//            .findById(orderId)
//            .orElseThrow(() -> new IllegalArgumentException(String.format("order with id %s not found", orderId)));
//    order.noteCreditReservationFailed();
//    domainEventPublisher.publish(OrderEntity.class,
//            orderId, singletonList(new OrderEntityRejectedEvent(order.getOrderEntityDetails())));
//  }
//
//  @Transactional
//  public OrderEntity cancelOrderEntity(Long orderId) {
//    OrderEntity order = orderRepository
//            .findById(orderId)
//            .orElseThrow(() -> new IllegalArgumentException(String.format("order with id %s not found", orderId)));
//    order.cancel();
//    domainEventPublisher.publish(OrderEntity.class,
//            orderId, singletonList(new OrderEntityCancelledEvent(order.getOrderEntityDetails())));
//    return order;
//  }
}

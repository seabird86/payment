package com.anhnt.payment.repository.entity;


import com.anhnt.payment.constant.OrderState;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import java.math.BigDecimal;

@Entity
@Table(name="tr_order")
@Access(AccessType.FIELD)
@NoArgsConstructor
@Getter
@Setter
public class OrderEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  private OrderState state;

  private Long customerId;

  private BigDecimal amount;

//  @Embedded
//  private OrderDetails orderDetails;

  @Version
  private Long version;

//  public OrderEntity() {
//  }
//
//  public OrderEntity(OrderDetails orderDetails) {
//    this.orderDetails = orderDetails;
//    this.state = OrderState.PENDING;
//  }

//  public static ResultWithEvents<OrderEntity> createOrder(OrderDetails orderDetails) {
//    OrderEntity order = new OrderEntity(orderDetails);
//    OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent(orderDetails);
//    return new ResultWithEvents<>(order, singletonList(orderCreatedEvent));
//  }
//
//  public Long getId() {
//    return id;
//  }
//
//  public void noteCreditReserved() {
//    this.state = OrderState.APPROVED;
//  }
//
//  public void noteCreditReservationFailed() {
//    this.state = OrderState.REJECTED;
//  }

//  public OrderState getState() {
//    return state;
//  }
//
//  public OrderDetails getOrderDetails() {
//    return orderDetails;
//  }
//
//  public void cancel() {
//    switch (state) {
//      case PENDING:
//        throw new PendingOrderCantBeCancelledException();
//      case APPROVED:
//        this.state = OrderState.CANCELLED;
//        return;
//      default:
//        throw new UnsupportedOperationException("Can't cancel in this state: " + state);
//    }
//  }
}

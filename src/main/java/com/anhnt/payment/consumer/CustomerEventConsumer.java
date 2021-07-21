package com.anhnt.payment.consumer;

import com.anhnt.common.domain.customer.event.CustomerCreditReservationFailedEvent;
import com.anhnt.common.domain.customer.event.CustomerCreditReservedEvent;
import com.anhnt.common.domain.customer.event.CustomerValidationFailedEvent;
import com.anhnt.payment.service.OrderService;
import org.springframework.stereotype.Service;
import io.eventuate.tram.events.subscriber.DomainEventEnvelope;
import io.eventuate.tram.events.subscriber.DomainEventHandlers;
import io.eventuate.tram.events.subscriber.DomainEventHandlersBuilder;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class CustomerEventConsumer {
  @Autowired
  private OrderService orderService;

  public DomainEventHandlers domainEventHandlers() {
    return DomainEventHandlersBuilder
            .forAggregateType("io.eventuate.examples.tram.ordersandcustomers.customers.domain.Customer")
            .onEvent(CustomerCreditReservedEvent.class, this::handleCustomerCreditReservedEvent)
            .onEvent(CustomerCreditReservationFailedEvent.class, this::handleCustomerCreditReservationFailedEvent)
            .onEvent(CustomerValidationFailedEvent.class, this::handleCustomerValidationFailedEvent)
            .build();
  }

  private void handleCustomerCreditReservedEvent(DomainEventEnvelope<CustomerCreditReservedEvent> domainEventEnvelope) {
    // TODO
//    orderService.approveOrder(domainEventEnvelope.getEvent().getOrderId());
  }

  private void handleCustomerCreditReservationFailedEvent(DomainEventEnvelope<CustomerCreditReservationFailedEvent> domainEventEnvelope) {
    // TODO
//    orderService.rejectOrder(domainEventEnvelope.getEvent().getOrderId());
  }

  private void handleCustomerValidationFailedEvent(DomainEventEnvelope<CustomerValidationFailedEvent> domainEventEnvelope) {
    // TODO
//    orderService.rejectOrder(domainEventEnvelope.getEvent().getOrderId());
  }

}

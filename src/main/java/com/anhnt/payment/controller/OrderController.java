package com.anhnt.payment.controller;

import com.anhnt.payment.controller.request.CreateOrderRequest;
import com.anhnt.payment.repository.OrderRepository;
import com.anhnt.payment.repository.entity.OrderEntity;
import com.anhnt.payment.service.OrderService;
import io.eventuate.common.json.mapper.JSonMapper;
import io.eventuate.tram.viewsupport.rebuild.DomainSnapshotExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

  @Autowired
  private OrderService orderService;
  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private DomainSnapshotExportService<OrderEntity> domainSnapshotExportService;

  @RequestMapping(value = "/orders", method = RequestMethod.POST)
  public Long createOrder(@RequestBody CreateOrderRequest createOrderRequest) {
    OrderEntity order = orderService.createOrder(createOrderRequest);
    return order.getId();
  }

//  @RequestMapping(value="/orders/{orderId}", method= RequestMethod.GET)
//  public ResponseEntity<GetOrderResponse> getOrder(@PathVariable Long orderId) {
//     return orderRepository
//            .findById(orderId)
//            .map(this::makeSuccessfulResponse)
//            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//  }
//
//  @RequestMapping(value="/orders/{orderId}/cancel", method= RequestMethod.POST)
//  public ResponseEntity<GetOrderResponse> cancelOrder(@PathVariable Long orderId) {
//     Order order = orderService.cancelOrder(orderId);
//     return makeSuccessfulResponse(order);
//  }

//  @RequestMapping(value = "/orders/make-snapshot", method = RequestMethod.POST)
//  public String makeSnapshot() {
//    return JSonMapper.toJson(domainSnapshotExportService.exportSnapshots());
//  }
//
//  private ResponseEntity<GetOrderResponse> makeSuccessfulResponse(Order order) {
//    return new ResponseEntity<>(new GetOrderResponse(order.getId(), order.getState()), HttpStatus.OK);
//  }
}

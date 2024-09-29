package com.cirin0.storecomponents.controller;

import com.cirin0.storecomponents.model.Order;
import com.cirin0.storecomponents.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {
  private final OrderService orderService;

  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @GetMapping
  public List<Order> getOrdersForUser() {
    return orderService.getAllOrders();
  }

  @PostMapping
  public void createOrder(@RequestBody Order order) {
    orderService.createOrder(order);
  }
}
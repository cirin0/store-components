package com.cirin0.storecomponents.controller;

import com.cirin0.storecomponents.model.Order;
import com.cirin0.storecomponents.model.User;
import com.cirin0.storecomponents.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
  private final OrderService orderService;

  @GetMapping
  public List<Order> getAllOrders() {
    return orderService.getAllOrders();
  }

  @GetMapping("/{id}")
  public Order getOrderById(Long id) {
    return orderService.getOrderById(id);
  }

  @PostMapping("/create")
  public Order createOrder(@RequestBody Order order, Authentication authentication) {
    User user = (User) authentication.getPrincipal();
    order.setUser(user);
    return orderService.createOrder(order);
  }
}
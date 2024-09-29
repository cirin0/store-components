package com.cirin0.storecomponents.controller;

import com.cirin0.storecomponents.model.Order;
import com.cirin0.storecomponents.model.User;
import com.cirin0.storecomponents.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {
  @Autowired
  private OrderService orderService;

  @GetMapping
  public List<Order> getOrdersForUser(Authentication auth) {
    User user = (User) auth.getPrincipal();
    return orderService.getOrdersForUser(user.getId());
  }

  @PostMapping
  public Order createOrder(@RequestBody Order order) {
    return orderService.createOrder(order);
  }
}
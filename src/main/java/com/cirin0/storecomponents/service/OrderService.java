package com.cirin0.storecomponents.service;

import com.cirin0.storecomponents.model.Order;
import com.cirin0.storecomponents.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
  @Autowired
  private OrderRepository orderRepository;

  public List<Order> getOrdersForUser(Long userId) {
    return orderRepository.findByUserId(userId);
  }

  public List<Order> getAllOrders() {
    return orderRepository.findAll();
  }

  public Order createOrder(Order order) {
    return orderRepository.save(order);
  }
}

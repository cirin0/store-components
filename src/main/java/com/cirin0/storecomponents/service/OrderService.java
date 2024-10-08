package com.cirin0.storecomponents.service;

import com.cirin0.storecomponents.model.Order;
import com.cirin0.storecomponents.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
  private final OrderRepository orderRepository;

  public List<Order> getAllOrders() {
    return orderRepository.findAll();
  }

  public Order getOrderById(Long id) {
    return orderRepository.findById(id).orElse(null);
  }

  public Order createOrder(Order order) {
    // 1. Calculate the total price based on order items.
    // 2. Check product stock levels and update accordingly.
    return orderRepository.save(order);
  }
}

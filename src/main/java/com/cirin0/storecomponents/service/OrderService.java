package com.cirin0.storecomponents.service;

import com.cirin0.storecomponents.model.Order;
import com.cirin0.storecomponents.repository.OrderRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderService {

  private final OrderRepository orderRepository;

  public OrderService(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  public List<Order> getAllOrders() {
    return orderRepository.findAll();
  }

  public void createOrder(Order order) {
    orderRepository.save(order);
  }
}

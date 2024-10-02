package com.cirin0.storecomponents.service;

import com.cirin0.storecomponents.model.OrderItem;
import com.cirin0.storecomponents.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderItemService {

  private final OrderItemRepository orderItemRepository;

  public List<OrderItem> getAllOrderItems() {
    return orderItemRepository.findAll();
  }

  public Optional<OrderItem> getOrderItemById(Long id) {
    return orderItemRepository.findById(id);
  }

  public OrderItem createOrderItem(OrderItem orderItem) {
    return orderItemRepository.save(orderItem);
  }
}

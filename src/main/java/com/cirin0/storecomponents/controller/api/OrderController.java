package com.cirin0.storecomponents.controller.api;

import com.cirin0.storecomponents.dto.OrderDTO;
import com.cirin0.storecomponents.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
  private final OrderService orderService;

  @GetMapping
  public ResponseEntity<List<OrderDTO>> getOrders() {
    return ResponseEntity.ok(orderService.getAllOrders());
  }

  @GetMapping("/{id}")
  public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) {
    return ResponseEntity.ok(orderService.getOrderById(id));
  }

  @GetMapping("/user/{userId}")
  public ResponseEntity<List<OrderDTO>> getOrdersByUserId(@PathVariable Long userId) {
    return ResponseEntity.ok(orderService.getOrdersByUserId(userId));
  }

  @PostMapping()
  public ResponseEntity<OrderDTO> createOrder(@RequestParam("userId") Long userId) {
    return ResponseEntity.ok(orderService.createOrder(userId));
  }
}
package com.cirin0.storecomponents.service;

import com.cirin0.storecomponents.dto.OrderDTO;
import com.cirin0.storecomponents.mapper.OrderMapper;
import com.cirin0.storecomponents.model.*;
import com.cirin0.storecomponents.repository.CartRepository;
import com.cirin0.storecomponents.repository.OrderRepository;
import com.cirin0.storecomponents.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
  private final CartService cartService;
  private final OrderRepository orderRepository;
  private final OrderMapper orderMapper;
  private final CartRepository cartRepository;
  private final ProductRepository productRepository;

  public List<OrderDTO> getAllOrders() {
    List<Order> orders = orderRepository.findAll();
    return orderMapper.toDtoList(orders);
  }

  public OrderDTO getOrderById(Long Id) {
    Order order = orderRepository.findById(Id)
        .orElseThrow(() -> new RuntimeException("Order not found " + Id));
    return orderMapper.toDto(order);
  }

  public List<OrderDTO> getOrdersByUserId(Long userId) {
    return orderRepository.findByUserId(userId).stream()
        .map(orderMapper::toDto)
        .collect(Collectors.toList());
  }

  public OrderDTO createOrder(Long userId) {
    Cart cart = cartRepository.findByUserId(userId)
        .orElseThrow(() -> new RuntimeException("Cart not found for user " + userId));
    Order order = new Order();
    order.setUser(cart.getUser());
    order.setTotalPrice(cart.getTotalPrice());

    for (CartItem cartItem : cart.getItems()) {
      Long productId = cartItem.getProduct().getId();
      Integer quantity = cartItem.getQuantity();
      Product product = productRepository.findById(productId)
          .orElseThrow(() -> new RuntimeException("Product not found " + productId));
      OrderItem orderItem = new OrderItem();
      orderItem.setOrder(order);
      orderItem.setProduct(product);
      orderItem.setQuantity(quantity);
      orderItem.setPrice(product.getPrice());
      order.getItems().add(orderItem);
    }
    orderRepository.save(order);
    cartService.clearCart(userId);
    return orderMapper.toDto(order);
  }

//  public OrderDTO createOrder(Long userId) {
////    Cart cart = cartRepository.findByUserId(userId)
////        .orElseThrow(() -> new RuntimeException("Cart not found for user " + userId));
//    var cartDto = cartService.getCartByUserId(userId);
//    if (cartDto.getItems().isEmpty()) {
//      throw new RuntimeException("Cannot create order with empty cart");
//    }
//    Order order = new Order();
//    order.setUser(User.builder().id(userId).build());
//
//    cartDto.getItems().forEach(cartItem -> {
//      OrderItem orderItem = new OrderItem();
//      orderItem.setOrder(order);
//      orderItem.setProduct(Product.builder()
//          .id(cartItem.getProductId())
//          .name(cartItem.getProductName())
//          .build());
//      orderItem.setQuantity(cartItem.getQuantity());
//      orderItem.setPrice(cartItem.getPrice());
//      order.getItems().add(orderItem);
//    });
//    order.calculateTotalPrice();
//    Order savedOrder = orderRepository.save(order);
//    cartService.clearCart(userId);
//    return orderMapper.toDto(savedOrder);
//  }


}

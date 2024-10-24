package com.cirin0.storecomponents.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDTO {
  private Long id;
  private Long userId;
  private List<CartItemDTO> items;
  private double totalPrice;
  private LocalDateTime orderDate;
}

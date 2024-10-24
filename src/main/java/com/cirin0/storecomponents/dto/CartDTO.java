package com.cirin0.storecomponents.dto;

import lombok.Data;

import java.util.List;

@Data
public class CartDTO {
  private Long id;
  private Long userId;
  private List<CartItemDTO> items;
  private double totalPrice;
}

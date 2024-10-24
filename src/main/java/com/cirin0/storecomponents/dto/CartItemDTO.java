package com.cirin0.storecomponents.dto;

import lombok.Data;

@Data
public class CartItemDTO {
  private Long id;
  private Long productId;
  private int quantity;
  //private double price;
}

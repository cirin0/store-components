package com.cirin0.storecomponents.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItemDTO {
  private Long id;
  private Long productId;
  private String productName;
  private Integer quantity;
  private BigDecimal totalPrice;
}

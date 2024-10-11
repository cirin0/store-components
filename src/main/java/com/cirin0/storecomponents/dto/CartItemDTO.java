package com.cirin0.storecomponents.dto;

import lombok.Data;

@Data
public class CartItemDTO {
  private Long productId;
  private Integer quantity;
}

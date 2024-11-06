package com.cirin0.storecomponents.dto;

import lombok.Data;

@Data
public class AddToCartDTO {
  private Long productId;
  private Integer quantity;
}

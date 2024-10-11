package com.cirin0.storecomponents.dto;

import lombok.Data;

@Data
public class ProductDTO {
  private String name;
  private double price;
  private String description;
  private String imageUrl;
  private Long categoryId;
}

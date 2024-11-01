package com.cirin0.storecomponents.dto;

import lombok.Data;

@Data
public class ProductRequest {
  private Long id;
  private String name;
  private Double price;
  private String description;
  private String imageUrl;
  private Long categoryId;
}

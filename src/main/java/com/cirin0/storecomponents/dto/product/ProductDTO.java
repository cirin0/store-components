package com.cirin0.storecomponents.dto.product;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductDTO {
  private Long id;
  private String name;
  private String imageUrl;
  private String description;
  private Double price;
  private LocalDateTime createdAt;
  private Long categoryId;
  private String categoryName;
}

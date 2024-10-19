package com.cirin0.storecomponents.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ProductDTO {
  private Long id;
  private String name;
  private double price;
  private String description;
  private String imageUrl;
  private LocalDateTime createdAt;
  private Long categoryId;
}

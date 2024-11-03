package com.cirin0.storecomponents.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ProductCreate {
  @NotBlank(message = "Name is required")
  private String name;

  private String imageUrl;
  private String description;

  @NotNull(message = "Price is required")
  @Positive(message = "Price must be positive")
  private Double price;

  @NotNull(message = "Category ID is required")
  private Long categoryId;
}

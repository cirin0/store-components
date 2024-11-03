package com.cirin0.storecomponents.dto.category;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryCreate {
  @NotBlank(message = "Name is required")
  private String name;
  private String imageUrl;
}

package com.cirin0.storecomponents.dto;

import lombok.Data;

@Data
public class CategoryRequest {
  private Long id;
  private String name;
  private String imageUrl;
}

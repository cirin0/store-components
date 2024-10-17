package com.cirin0.storecomponents.dto;

import com.cirin0.storecomponents.model.Product;
import lombok.Data;

import java.util.List;

@Data
public class CategoryDTO {
  private Long id;
  private String name;
  private String imageUrl;
  private List<Product> product;
}

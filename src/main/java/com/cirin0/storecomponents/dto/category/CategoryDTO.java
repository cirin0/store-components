package com.cirin0.storecomponents.dto.category;

import com.cirin0.storecomponents.dto.product.ProductDTO;
import lombok.Data;

import java.util.List;

@Data
public class CategoryDTO {
  private Long id;
  private String name;
  private String imageUrl;
  private List<ProductDTO> productIds;
}

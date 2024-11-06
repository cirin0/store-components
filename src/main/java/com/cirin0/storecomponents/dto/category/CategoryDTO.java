package com.cirin0.storecomponents.dto.category;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
  private Long id;
  private String name;
  private String imageUrl;
}
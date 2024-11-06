package com.cirin0.storecomponents.mapper;

import com.cirin0.storecomponents.dto.product.ProductDTO;
import com.cirin0.storecomponents.dto.product.ProductUpdate;
import com.cirin0.storecomponents.model.Category;
import com.cirin0.storecomponents.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {
  ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

  @Mapping(target = "categoryId", source = "category.id")
  @Mapping(target = "categoryName", source = "category.name")
  ProductDTO toDTO(Product product);

  List<ProductDTO> toDTOList(List<Product> products);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "category", ignore = true)
  void updateEntityFromDTO(ProductUpdate productUpdate, @MappingTarget Product product);

  default void updateCategory(Product product, Category category) {
    if (product != null && category != null) {
      product.setCategory(category);
    }
  }
}

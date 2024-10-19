package com.cirin0.storecomponents.mapper;

import com.cirin0.storecomponents.dto.ProductDTO;
import com.cirin0.storecomponents.model.Category;
import com.cirin0.storecomponents.model.Product;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductMapper {
  ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

  @Mapping(target = "category", ignore = true)
  void updateProductFromDTO(ProductDTO productDTO, @MappingTarget Product product);

  @Mapping(target = "categoryId", source = "category.id")
  ProductDTO toDTO(Product product);

  @Mapping(target = "category", source = "categoryId", qualifiedByName = "mapCategory")
  Product toEntity(ProductDTO productDTO);

  List<ProductDTO> toDTOList(List<Product> products);

  @Named("mapCategory")
  default Category mapCategory(Long categoryId) {
    if (categoryId == null) {
      return null;
    }
    Category category = new Category();
    category.setId(categoryId);
    return category;
  }
}

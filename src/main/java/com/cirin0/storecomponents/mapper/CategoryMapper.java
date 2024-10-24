package com.cirin0.storecomponents.mapper;

import com.cirin0.storecomponents.dto.CategoryDTO;
import com.cirin0.storecomponents.dto.CategoryRequest;
import com.cirin0.storecomponents.model.Category;
import com.cirin0.storecomponents.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

  CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

  @Mapping(target = "products", ignore = true)
  Category toEntity(CategoryRequest categoryRequest);

  @Mapping(source = "products", target = "productIds", qualifiedByName = "mapProductIds")
  CategoryDTO toDTO(Category category);

  List<CategoryDTO> toDTOList(List<Category> categories);

  @Mapping(target = "products", ignore = true)
  void updateCategoryFromDTO(CategoryRequest categoryRequest, @MappingTarget Category category);

  @Named("mapProductIds")
  default List<Long> mapProductIds(List<Product> productIds) {
    return productIds.stream().map(Product::getId).toList();
  }

}

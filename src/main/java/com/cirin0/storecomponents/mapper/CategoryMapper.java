package com.cirin0.storecomponents.mapper;

import com.cirin0.storecomponents.dto.CategoryDTO;
import com.cirin0.storecomponents.dto.CategoryRequestDTO;
import com.cirin0.storecomponents.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

  CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

  @Mapping(target = "products", ignore = true)
  Category toEntity(CategoryRequestDTO categoryRequestDTO);

  @Mapping(target = "products", source = "products")
  CategoryDTO toDTO(Category category);

  List<CategoryDTO> toDTOList(List<Category> categories);

  @Mapping(target = "products", ignore = true)
  void updateCategoryFromDTO(CategoryRequestDTO categoryRequestDTO, @MappingTarget Category category);
}

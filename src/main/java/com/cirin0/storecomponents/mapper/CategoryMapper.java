package com.cirin0.storecomponents.mapper;

import com.cirin0.storecomponents.model.Category;
import com.cirin0.storecomponents.dto.category.CategoryDTO;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {

  @Mapping(target = "products", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  Category toEntity(CategoryDTO categoryDto);

  CategoryDTO toDto(Category category);

  List<CategoryDTO> toDtoList(List<Category> categories);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  Category partialUpdate(CategoryDTO categoryDto, @MappingTarget Category category);
}

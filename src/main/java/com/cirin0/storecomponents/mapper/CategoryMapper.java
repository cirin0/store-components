package com.cirin0.storecomponents.mapper;

import com.cirin0.storecomponents.dto.category.CategoryCreate;
import com.cirin0.storecomponents.dto.category.CategoryDTO;
import com.cirin0.storecomponents.dto.category.CategoryWithoutProducts;
import com.cirin0.storecomponents.model.Category;
import com.cirin0.storecomponents.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring",
    uses = {ProductMapper.class},
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

  CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

  @Mapping(target = "products", source = "products")
  CategoryDTO toDTO(Category category);

  List<CategoryDTO> toDTOList(List<Category> categories);

  CategoryWithoutProducts toDTOWithoutProducts(Category category);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "products", ignore = true)
  Category toEntity(CategoryCreate categoryCreate);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "products", ignore = true)
  void updateEntity(CategoryCreate categoryCreate, @MappingTarget Category category);

  default void addProduct(Category category, Product product) {
    if (category != null && product != null) {
      if (category.getProducts() == null) {
        category.setProducts(new ArrayList<>());
      }
      category.getProducts().add(product);
      product.setCategory(category);
    }
  }

  default void removeProduct(Category category, Product product) {
    if (category != null && product != null && category.getProducts() != null) {
      category.getProducts().remove(product);
      product.setCategory(null);
    }
  }
}

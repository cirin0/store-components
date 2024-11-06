package com.cirin0.storecomponents.mapper;

import com.cirin0.storecomponents.dto.product.ProductDTO;
import com.cirin0.storecomponents.model.Product;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {

  Product toEntity(ProductDTO productDTO);

  @Mapping(target = "categoryId", source = "category.id")
  @Mapping(target = "categoryName", source = "category.name")
  ProductDTO toDto(Product product);

  List<ProductDTO> toDtoList(List<Product> products);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  Product partialUpdate(ProductDTO productDTO, @MappingTarget Product product);
}

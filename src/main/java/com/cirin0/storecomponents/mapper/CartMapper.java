package com.cirin0.storecomponents.mapper;

import com.cirin0.storecomponents.dto.CartDTO;
import com.cirin0.storecomponents.model.Cart;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CartMapper {
  Cart toEntity(CartDTO cartDTO);

  CartDTO toDto(Cart cart);

  List<CartDTO> toDtoList(List<Cart> carts);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  Cart partialUpdate(CartDTO cartDTO, @MappingTarget Cart cart);
}
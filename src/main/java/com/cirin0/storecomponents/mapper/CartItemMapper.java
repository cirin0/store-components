package com.cirin0.storecomponents.mapper;

import com.cirin0.storecomponents.dto.CartItemDTO;
import com.cirin0.storecomponents.model.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface CartItemMapper {
  @Mapping(source = "product.id", target = "productId")
  CartItemDTO toDto(CartItem cartItem);

  @Mapping(source = "productId", target = "product.id")
  CartItem toEntity(CartItemDTO cartItemDTO);
}

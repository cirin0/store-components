package com.cirin0.storecomponents.mapper;

import com.cirin0.storecomponents.dto.CartDTO;
import com.cirin0.storecomponents.dto.CartItemDTO;
import com.cirin0.storecomponents.model.Cart;
import com.cirin0.storecomponents.model.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CartMapper {

  CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);

  CartDTO toDto(Cart cart);

  Cart toEntity(CartDTO cartDTO);

  CartItemDTO toDto(CartItem cartItem);

  @Mapping(target = "cart", ignore = true)
  CartItem toEntity(CartItemDTO cartItemDTO);
}

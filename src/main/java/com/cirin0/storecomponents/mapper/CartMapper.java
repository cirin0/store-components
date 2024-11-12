package com.cirin0.storecomponents.mapper;

import com.cirin0.storecomponents.dto.CartDTO;
import com.cirin0.storecomponents.dto.CartItemDTO;
import com.cirin0.storecomponents.model.Cart;
import com.cirin0.storecomponents.model.CartItem;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CartMapper {
  @Mapping(target = "items", source = "items")
  @Mapping(target = "userId", source = "user.id")
  CartDTO toDto(Cart cart);

  List<CartItemDTO> toCartItemDtoList(List<CartItem> items);

  @Mapping(target = "productId", source = "product.id")
  @Mapping(target = "productName", source = "product.name")
  @Mapping(target = "price", source = "product.price")
  @Mapping(target = "totalPrice", expression = "java(cartItem.getProduct().getPrice() * cartItem.getQuantity())")
  CartItemDTO toDto(CartItem cartItem);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  Cart partialUpdate(CartDTO cartDTO, @MappingTarget Cart cart);
}
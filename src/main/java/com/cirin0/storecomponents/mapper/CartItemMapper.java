package com.cirin0.storecomponents.mapper;

import com.cirin0.storecomponents.dto.AddToCartDTO;
import com.cirin0.storecomponents.dto.CartItemDTO;
import com.cirin0.storecomponents.model.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CartItemMapper {
  @Mapping(target = "productId", source = "product.id")
  @Mapping(target = "productName", source = "product.name")
  @Mapping(target = "productPrice", source = "product.price")
  @Mapping(target = "totalPrice", expression = "java(cartItem.getProductPrice().multiply(new java.math.BigDecimal(cartItem.getQuantity())))")
  CartItemDTO toDto(CartItem cartItem);

  AddToCartDTO toAddToCartDto(CartItem cartItem);

  CartItem toEntityAddToCart(AddToCartDTO addToCartDTO);

  CartItem toEntity(CartItemDTO cartItemDTO);
}

package com.cirin0.storecomponents.mapper;

import com.cirin0.storecomponents.dto.OrderDTO;
import com.cirin0.storecomponents.dto.OrderItemDTO;
import com.cirin0.storecomponents.model.CartItem;
import com.cirin0.storecomponents.model.Order;
import com.cirin0.storecomponents.model.OrderItem;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {

  @Mapping(target = "items", source = "items")
  @Mapping(target = "userId", source = "user.id")
  OrderDTO toDto(Order order);

  List<OrderDTO> toDtoList(List<Order> orders);

  List<OrderItemDTO> toOrderItemDtoList(List<OrderItem> items);

  @Mapping(target = "productId", source = "product.id")
  @Mapping(target = "productName", source = "product.name")
  @Mapping(target = "price", source = "product.price")
  @Mapping(target = "totalPrice", expression = "java(orderItem.getPrice() * orderItem.getQuantity())")
  OrderItemDTO toDto(OrderItem orderItem);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  Order partialUpdate(OrderDTO orderDTO, @MappingTarget Order order);

//  @Mapping(target = "userId", source = "user.id")
//  OrderDTO toDto(Order order);
//
//  List<OrderDTO> toDtoList(List<Order> orders);
//
//  @Mapping(target = "productId", source = "product.id")
//  @Mapping(target = "productName", source = "product.name")
//  OrderItemDTO toDto(OrderItem orderItem);
//
//  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//  Order partialUpdate(OrderDTO orderDTO, @MappingTarget Order order);
}

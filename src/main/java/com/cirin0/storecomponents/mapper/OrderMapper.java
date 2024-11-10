package com.cirin0.storecomponents.mapper;

import com.cirin0.storecomponents.dto.OrderDTO;
import com.cirin0.storecomponents.model.Order;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {
  Order toEntity(OrderDTO orderDTO);

  @Mapping(target = "userId", source = "user.id")
  OrderDTO toDto(Order order);

  List<OrderDTO> toDtoList(List<Order> orders);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  Order partialUpdate(OrderDTO orderDTO, @MappingTarget Order order);
}

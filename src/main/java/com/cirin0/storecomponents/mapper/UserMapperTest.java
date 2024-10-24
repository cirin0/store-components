package com.cirin0.storecomponents.mapper;

import com.cirin0.storecomponents.dto.UserDTO;
import com.cirin0.storecomponents.dto.UserRequestDTO;
import com.cirin0.storecomponents.model.Order;
import com.cirin0.storecomponents.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapperTest {
  UserMapperTest INSTANCE = Mappers.getMapper(UserMapperTest.class);

  // Мапимо UserRequestDTO на User
  @Mapping(target = "role", defaultValue = "USER")
  User toEntity(UserRequestDTO userRequestDTO);

  // Мапимо User на UserDTO
  @Mapping(source = "orders", target = "orderIds", qualifiedByName = "mapOrderIds")
  @Mapping(source = "cart.id", target = "cartId")
  UserDTO toDTO(User user);

  @Named("mapOrderIds")
  default List<Long> mapOrderIds(List<Order> orders) {
    if (orders == null) {
      return null;
    }
    return orders.stream()
        .map(Order::getId)
        .collect(Collectors.toList());
  }
}

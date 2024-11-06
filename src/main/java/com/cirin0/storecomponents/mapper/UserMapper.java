package com.cirin0.storecomponents.mapper;

import com.cirin0.storecomponents.dto.user.UserDTO;
import com.cirin0.storecomponents.dto.user.UserDetailsDTO;
import com.cirin0.storecomponents.dto.user.UserRegister;
import com.cirin0.storecomponents.dto.user.UserUpdate;
import com.cirin0.storecomponents.model.Cart;
import com.cirin0.storecomponents.model.Order;
import com.cirin0.storecomponents.model.User;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;

@Mapper(componentModel = "spring",
    uses = {OrderMapper.class, CartMapper.class},
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

  @Mapping(target = "password", ignore = true)
  UserDTO toDTO(User user);

  @Mapping(target = "password", ignore = true)
  UserDetailsDTO toDetailsDTO(User user);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "role", ignore = true)
  @Mapping(target = "cart", ignore = true)
  @Mapping(target = "orders", ignore = true)
  User toEntity(UserRegister userRegister);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "password", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "role", ignore = true)
  @Mapping(target = "cart", ignore = true)
  @Mapping(target = "orders", ignore = true)
  void updateEntity(UserUpdate userUpdate, @MappingTarget User user);

  @Named("toUserSummary")
  @Mapping(target = "password", ignore = true)
  @Mapping(target = "orders", ignore = true)
  @Mapping(target = "cart", ignore = true)
  UserDTO toUserSummary(User user);

  default void addOrder(User user, Order order) {
    if (user != null && order != null) {
      if (user.getOrders() == null) {
        user.setOrders(new ArrayList<>());
      }
      user.getOrders().add(order);
      order.setUser(user);
    }
  }

  default void setCart(User user, Cart cart) {
    if (user != null) {
      user.setCart(cart);
      cart.setUser(user);
    }
  }
}

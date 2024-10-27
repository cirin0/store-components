package com.cirin0.storecomponents.mapper;

import com.cirin0.storecomponents.dto.UserDTO;
import com.cirin0.storecomponents.dto.UserRegister;
import com.cirin0.storecomponents.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

  @Mapping(target = "role", source = "role", defaultValue = "USER")
  UserDTO toDTO(User user);

  User userToEntity(UserDTO userDTO);

  //@Mapping(target = "id", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "password", ignore = true)
  User toEntity(UserRegister userRegister);
}

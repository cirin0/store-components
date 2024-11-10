package com.cirin0.storecomponents.mapper;

import com.cirin0.storecomponents.dto.user.UserDTO;
import com.cirin0.storecomponents.dto.user.UserRegister;
import com.cirin0.storecomponents.dto.user.UserUpdate;
import com.cirin0.storecomponents.model.User;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
  User toEntity(UserDTO userDTO);

  UserDTO toDto(User user);

  User toUpdateEntity(UserUpdate userUpdate);

  UserUpdate toUpdateDTO(User user);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "role", constant = "USER")
  @Mapping(target = "createdAt", constant = "java.time.LocalDateTime.now()")
  User toRegisterEntity(UserRegister userRegister);

  UserRegister toRegisterDTO(User user);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  void partialUpdate(UserUpdate userUpdate, @MappingTarget User user);
}
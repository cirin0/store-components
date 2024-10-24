package com.cirin0.storecomponents.mapper;

import com.cirin0.storecomponents.dto.UserDTO;
import com.cirin0.storecomponents.dto.UserRequestDTO;
import com.cirin0.storecomponents.dto.UserResponseDTO;
import com.cirin0.storecomponents.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

  //@Mapping(source = "role", target = "role")
  UserResponseDTO userDTOToUserResponseDTO(UserDTO userDTO);

  @Mapping(target = "role", source = "role", defaultValue = "USER")
  @Mapping(target = "firstName", ignore = true)
  @Mapping(target = "lastName", ignore = true)
  UserDTO userRequestDTOToUserDTO(UserRequestDTO userRequestDTO);

  User userDTOToUser(UserDTO userDTO);

  UserDTO userToUserDTO(User user);

  List<UserResponseDTO> toDTOList(List<User> users);

  void updateUserFromDTO(UserRequestDTO userRequestDTO, @MappingTarget User user);
}

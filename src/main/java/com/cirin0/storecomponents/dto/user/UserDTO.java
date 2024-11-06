package com.cirin0.storecomponents.dto.user;

import com.cirin0.storecomponents.model.Role;
import lombok.Data;

import java.time.LocalDateTime;

//TODO: Доробити клас UserDTO (додати id і виправити помилки після додавання id)

@Data
public class UserDTO {
  private Long id;
  private String username;
  private String email;
  private LocalDateTime createdAt;
  private Role role;
}

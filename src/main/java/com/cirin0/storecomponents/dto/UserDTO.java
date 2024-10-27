package com.cirin0.storecomponents.dto;

import lombok.Data;

//TODO: Доробити клас UserDTO (додати id і виправити помилки після додавання id)

@Data
public class UserDTO {
  private Long id;
  private String username;
  private String email;
  private String role;
}

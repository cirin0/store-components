package com.cirin0.storecomponents.dto;

import com.cirin0.storecomponents.model.ERole;
import lombok.Data;

//TODO: Доробити клас UserDTO (додати id і виправити помилки після додавання id)

@Data
public class UserDTO {
  //  private Long id;
  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private ERole role;
}

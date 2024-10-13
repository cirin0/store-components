package com.cirin0.storecomponents.dto;

import com.cirin0.storecomponents.model.ERole;
import lombok.Data;

@Data
public class UserDTO {
  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private ERole role;
}

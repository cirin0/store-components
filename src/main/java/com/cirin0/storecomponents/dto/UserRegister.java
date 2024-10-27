package com.cirin0.storecomponents.dto;

import com.cirin0.storecomponents.model.Role;
import lombok.Data;

@Data
public class UserRegister {
  private String username;
  private String email;
  private String password;
  private Role role;
}

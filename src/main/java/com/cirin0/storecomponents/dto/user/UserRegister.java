package com.cirin0.storecomponents.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRegister {
  private String username;
  private String email;
  private String password;
}

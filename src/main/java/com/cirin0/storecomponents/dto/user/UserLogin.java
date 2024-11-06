package com.cirin0.storecomponents.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserLogin {
  private String email;
  private String password;
}

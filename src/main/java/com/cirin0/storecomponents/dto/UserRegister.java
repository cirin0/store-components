package com.cirin0.storecomponents.dto;

import com.cirin0.storecomponents.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRegister {
  private String username;
  @NotBlank(message = "Email is required")
  @Email(message = "Please provide a valid email")
  private String email;
  @NotBlank(message = "Password is required")
  private String password;
  private Role role;
}

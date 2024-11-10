package com.cirin0.storecomponents.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserRegister {
  @NotBlank
  private String username;
  @Email
  private String email;
  private String password;
  private LocalDateTime createdAt;
}

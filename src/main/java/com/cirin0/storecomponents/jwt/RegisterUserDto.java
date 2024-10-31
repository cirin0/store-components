package com.cirin0.storecomponents.jwt;

import lombok.Data;

@Data
public class RegisterUserDto {
  private String username;
  private String email;
  private String password;
}

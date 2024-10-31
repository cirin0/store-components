package com.cirin0.storecomponents.jwt;

import lombok.Data;

@Data
public class LoginUserDto {
  private String email;
  private String password;
}

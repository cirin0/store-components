package com.cirin0.storecomponents.config;

import lombok.Data;

@Data
public class LoginResponse {
  private String token;
  private long expiresIn;
}

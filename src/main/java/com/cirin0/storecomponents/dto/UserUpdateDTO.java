package com.cirin0.storecomponents.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserUpdateDTO {
  private String firstName;
  private String lastName;
  private String email;
}

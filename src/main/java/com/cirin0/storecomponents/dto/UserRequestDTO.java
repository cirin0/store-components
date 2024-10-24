package com.cirin0.storecomponents.dto;

import com.cirin0.storecomponents.model.ERole;
import lombok.Data;

@Data
public class UserRequestDTO {
  private Long id;
  private String email;
  private String password;
  private ERole role;
}

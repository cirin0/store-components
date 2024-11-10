package com.cirin0.storecomponents.dto.user;

import com.cirin0.storecomponents.dto.CartDTO;
import com.cirin0.storecomponents.dto.OrderDTO;
import com.cirin0.storecomponents.model.Role;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class UserDetailsDTO {
  private Long id;
  private String username;
  private String email;
  private LocalDateTime createdAt;
  private Role role;
  private List<OrderDTO> orders;
  private CartDTO cart;
}

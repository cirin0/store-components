package com.cirin0.storecomponents.dto;

import com.cirin0.storecomponents.dto.user.UserDTO;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class CartDTO {
  private List<CartItemDTO> items;
  private BigDecimal totalPrice;
}
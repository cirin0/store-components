package com.cirin0.storecomponents.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cart {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", unique = true)
  private User user;

  @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<CartItem> items = new ArrayList<>();

  private BigDecimal totalPrice = BigDecimal.ZERO;

  public void recalculateTotal() {
    totalPrice = items
        .stream()
        .map(CartItem::getTotalPrice)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }
}

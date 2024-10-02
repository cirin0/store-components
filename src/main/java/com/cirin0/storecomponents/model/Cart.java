package com.cirin0.storecomponents.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Cart {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne(fetch = FetchType.LAZY)
  @MapsId
  private User user;

  @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
  private List<CartItem> cartItems;

  @Column(nullable = false)
  private double totalPrice;
}

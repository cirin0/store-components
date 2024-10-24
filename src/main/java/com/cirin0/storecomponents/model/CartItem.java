package com.cirin0.storecomponents.model;

import jakarta.persistence.*;
import lombok.*;


@Data
@Entity
public class CartItem {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "cart_id")
  private Cart cart;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id")
  private Product product;

  private int quantity;

  private double price;

  public void updatePrice() {
    price = product.getPrice() * quantity;
  }
}

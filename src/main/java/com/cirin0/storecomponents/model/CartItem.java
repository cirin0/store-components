package com.cirin0.storecomponents.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class CartItem {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "product_id")
  private Product product;

  private int quantity;

  public CartItem() {
  }

  public CartItem(Product product, int quantity) {
    this.product = product;
    this.quantity = quantity;
  }

  public double getTotalPrice() {
    return product.getPrice() * this.quantity;
  }
}

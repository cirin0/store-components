package com.cirin0.storecomponents.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
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

  @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<CartItem> items = new ArrayList<>();

  @Column(nullable = false)
  private double totalPrice;

  public void addItem(CartItem item) {
    items.add(item);
    item.setCart(this);
    recalculateTotalPrice();
  }

  public void removeItem(CartItem item) {
    items.remove(item);
    item.setCart(null);
    recalculateTotalPrice();
  }

  public void clearCart() {
    items.clear();
    recalculateTotalPrice();
  }

  public void recalculateTotalPrice() {
    totalPrice = items.stream()
        .mapToDouble(CartItem::getPrice)
        .sum();
  }
}

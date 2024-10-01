package com.cirin0.storecomponents.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "carts")
public class Cart {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
  private List<CartItem> items;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  public void addItem(CartItem item){
    items.add(item);
  }

  public void removeItem(CartItem item){
    items.remove(item);
  }

  public double getTotalPrice(){
    return items.stream().mapToDouble(CartItem::getTotalPrice).sum();
  }
}

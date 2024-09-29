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

  @OneToMany(cascade = CascadeType.ALL)
  private List<CartItem> items = new ArrayList<>();

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

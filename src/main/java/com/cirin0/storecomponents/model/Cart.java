package com.cirin0.storecomponents.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "cart")
public class Cart {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String cartId;

  @ManyToOne
  @JoinColumn(name = "users")
  private User username;
}

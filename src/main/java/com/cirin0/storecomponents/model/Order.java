package com.cirin0.storecomponents.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  private List<CartItem> items;

  private double totalPrice;

  @Column(nullable = false)
  private LocalDateTime orderDate = LocalDateTime.now();
}
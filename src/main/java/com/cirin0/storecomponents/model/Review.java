package com.cirin0.storecomponents.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
public class Review {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id", nullable = false)
  private Product product;

  private String review;

  @Column(nullable = false)
  private int rating;

  @Column(nullable = false)
  private LocalDateTime createdAt = LocalDateTime.now();
}

/*
Розібратись з цим до кінця
 */
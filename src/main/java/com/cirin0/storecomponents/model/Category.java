package com.cirin0.storecomponents.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class Category {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false) //, unique = true
  private String name;

  private String urlImage; // URL @Column(nullable = false)

  //@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
  //private List<Product> products;
}
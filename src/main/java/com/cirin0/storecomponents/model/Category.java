package com.cirin0.storecomponents.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
@Entity
public class Category {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false) //, unique = true
  private String name;

  private String imageUrl; // URL @Column(nullable = false)

  /*
  @ElementCollection
  @CollectionTable(name = "categoty-product", joinColumns = @JoinColumn(name = "category_id"))
  @Column(name = "product_id")

   */
  @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
  private List<Product> products = new ArrayList<>();
}
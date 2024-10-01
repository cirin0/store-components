package com.cirin0.storecomponents.model;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.*;

@Data
@Entity
//@Table(name = "products")
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private  String description;
  private double price;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;
}

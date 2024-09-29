package com.cirin0.storecomponents.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotEmpty
  @NotNull
  private String customerName;
  private String address;

  @OneToMany(cascade = CascadeType.ALL)
  private List<Product> products;
}
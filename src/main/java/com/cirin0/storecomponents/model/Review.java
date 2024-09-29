package com.cirin0.storecomponents.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "review")
public class Review {
  @Id
  private Long id;
  private String review;
  private int rating;
  @ManyToOne
  @JoinColumn(name = "product")
  private Product product;

  public void setProduct_Id(Long productId) {
    this.product = new Product();
    this.product.setId(productId);
  }
}

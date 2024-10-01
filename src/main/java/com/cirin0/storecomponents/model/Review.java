package com.cirin0.storecomponents.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "reviews")
public class Review {
  @Id
  private Long id;
  private String review;
  private int rating;
  @ManyToOne
  @JoinColumn(name = "product_id")
  private Product product;

  public void setProduct_Id(Long productId) {
    this.product = new Product();
    this.product.setId(productId);
  }
}

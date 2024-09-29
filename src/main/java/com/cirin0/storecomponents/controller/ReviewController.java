package com.cirin0.storecomponents.controller;

import com.cirin0.storecomponents.model.Review;
import com.cirin0.storecomponents.service.ReviewService;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController("/products/{productId}/reviews")
public class ReviewController {
  private final ReviewService reviewService;

  public ReviewController(ReviewService reviewService) {
    this.reviewService = reviewService;
  }
  @GetMapping
  public List<Review> getReviewsByProduct(@PathVariable Long productId) {
    return reviewService.getReviewsByProduct(productId);
  }
  @PostMapping
  public Review addReview(@PathVariable Long productId, Review review) {
    return reviewService.addReview(productId, review);
  }
}

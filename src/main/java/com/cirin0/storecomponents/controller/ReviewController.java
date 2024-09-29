package com.cirin0.storecomponents.controller;

import com.cirin0.storecomponents.model.Review;
import com.cirin0.storecomponents.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products/{productId}/reviews")
public class ReviewController {
  @Autowired
  private ReviewService reviewService;

  @GetMapping
  public List<Review> getReviewsByProduct(@PathVariable Long productId) {
    return reviewService.getReviewsByProduct(productId);
  }

  @PostMapping
  public Review addReview(@PathVariable Long productId, Review review) {
    return reviewService.addReview(productId, review);
  }
}

package com.cirin0.storecomponents.controller;

import com.cirin0.storecomponents.model.Review;
import com.cirin0.storecomponents.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products/{productId}/reviews")
@RequiredArgsConstructor
public class ReviewController {

  private final ReviewService reviewService;

  @GetMapping
  public List<Review> getReviewsByProductId(@PathVariable Long productId) {
    return reviewService.getReviewsByProductId(productId);
  }

  @PostMapping
  public Review addReview(@PathVariable Long productId, Review review) {
    return reviewService.createReview(productId, review);
  }

  @DeleteMapping("/{reviewId}")
  public void deleteReview(@PathVariable Long productId, @PathVariable Long reviewId) {
    reviewService.deleteReview(productId, reviewId);
  }

  @PutMapping("/{reviewId}")
  public Review updateReview(@PathVariable Long productId, @PathVariable Long reviewId, Review updatedReview) {
    return reviewService.updateReview(productId, reviewId, updatedReview);
  }
}

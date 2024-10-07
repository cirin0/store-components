/*package com.cirin0.storecomponents.controller;

import com.cirin0.storecomponents.model.Review;
import com.cirin0.storecomponents.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

  private final ReviewService reviewService;

  @PostMapping("/product/{productId}")
  public ResponseEntity<Review> createReview(@PathVariable Long productId, Review review) {
    Review createdReview = reviewService.createReview(productId, review);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdReview);
  }

  @GetMapping("/product/{productId}")
  public ResponseEntity<List<Review>> getReviewsByProductId(@PathVariable Long productId) {
    return ResponseEntity.ok(reviewService.getReviewsByProductId(productId));
  //public List<Review> getReviewsByProductId(@PathVariable Long productId) {
    //return reviewService.getReviewsByProductId(productId);
  }

  @GetMapping("/{reviewId}")
  public ResponseEntity<Review> getReviewById(@PathVariable Long reviewId) {
    return reviewService.getReviewById(reviewId)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }


//  @PutMapping("/{reviewId}")
//  public Review updateReview(@RequestParam Long productId, @PathVariable Long reviewId, Review updatedReview) {
//    return reviewService.updateReview(productId, reviewId, updatedReview);
//  }

  @DeleteMapping("/{reviewId}")
  public ResponseEntity<Void> deleteReview(@PathVariable Long reviewId) {
    reviewService.deleteReview(reviewId);
    return ResponseEntity.noContent().build();
  }
}
 */

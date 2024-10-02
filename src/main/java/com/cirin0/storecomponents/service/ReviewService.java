package com.cirin0.storecomponents.service;

import com.cirin0.storecomponents.model.Product;
import com.cirin0.storecomponents.model.Review;
import com.cirin0.storecomponents.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {

  private final ReviewRepository reviewRepository;
  private final ProductService productService;

  public List<Review> getReviewsByProductId(Long productId) {
    return reviewRepository.findByProductId(productId);
  }

  public Review createReview(Long productId, Review review) {
    Product product = productService.getProductById(productId);
    if (product == null) {
      throw new IllegalArgumentException("Product not found");
    }
    review.setProduct(product);
    return reviewRepository.save(review);
  }

  public void deleteReview(Long productId, Long reviewId) {
    Optional<Review> review = reviewRepository.findById(reviewId);
    if (review.isPresent() && review.get().getProduct().getId().equals(productId)) {
      reviewRepository.deleteById(reviewId);
    } else {
      throw new IllegalArgumentException("Review not found");
    }
  }

  public Review updateReview(Long productId, Long reviewId, Review updatedReview) {
    Optional<Review> review = reviewRepository.findById(reviewId);
    if (review.isPresent() && review.get().getProduct().getId().equals(productId)) {
      Review reviewToUpdate = review.get();
      reviewToUpdate.setRating(updatedReview.getRating());
      reviewToUpdate.setReview(updatedReview.getReview());
      return reviewRepository.save(reviewToUpdate);
    } else {
      throw new IllegalArgumentException("Review not found");
    }
  }
}

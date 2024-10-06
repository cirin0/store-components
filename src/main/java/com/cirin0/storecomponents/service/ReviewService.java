package com.cirin0.storecomponents.service;

import com.cirin0.storecomponents.model.Product;
import com.cirin0.storecomponents.model.Review;
import com.cirin0.storecomponents.repository.ProductRepository;
import com.cirin0.storecomponents.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {

  private final ReviewRepository reviewRepository;
  private final ProductRepository productRepository;

  public List<Review> getReviewsByProductId(Long productId) {
    return reviewRepository.findByProductId(productId);
  }
  public Optional<Review> getReviewById(Long reviewId) {
    return reviewRepository.findById(reviewId);
  }

  // Пофіксити метод
  public Review createReview(Long productId, Review review) {
    Optional<Product> productOptional = productRepository.findById(productId);
    if (productOptional.isPresent()) {
      Product product = productOptional.get();
      review.setProduct(product);
      return reviewRepository.save(review);
    } else {
      throw new IllegalArgumentException("Product not found with id: " + productId);
    }
  }

  public void deleteReview(Long reviewId) {
    reviewRepository.deleteById(reviewId);
  }

//  public Review updateReview(Long productId, Long reviewId, Review updatedReview) {
//    Optional<Review> review = reviewRepository.findById(reviewId);
//    if (review.isPresent() && review.get().getProduct().getId().equals(productId)) {
//      Review reviewToUpdate = review.get();
//      reviewToUpdate.setRating(updatedReview.getRating());
//      reviewToUpdate.setReview(updatedReview.getReview());
//      return reviewRepository.save(reviewToUpdate);
//    } else {
//      throw new IllegalArgumentException("Review not found");
//    }
//  }
}

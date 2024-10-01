package com.cirin0.storecomponents.service;

import com.cirin0.storecomponents.model.Review;
import com.cirin0.storecomponents.repository.ReviewRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReviewService {
  private  final ReviewRepository reviewRepository;

  public ReviewService(ReviewRepository reviewRepository) {
    this.reviewRepository = reviewRepository;
  }

  public List<Review> getReviewsByProduct(Long productId) {
    return reviewRepository.findByProductId(productId);
  }

  public Review addReview(Long productId, Review review) {
    review.setProduct_Id(productId);
    return reviewRepository.save(review);
  }
}

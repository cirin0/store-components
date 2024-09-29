package com.cirin0.storecomponents.repository;

import com.cirin0.storecomponents.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
  List<Review> findByProduct_Id(Long productId);

}

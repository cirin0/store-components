package com.cirin0.storecomponents.repository;

import com.cirin0.storecomponents.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
  List<Review> findByProduct_Id(Long productId);

}

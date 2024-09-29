package com.cirin0.storecomponents.repository;

import com.cirin0.storecomponents.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
  List<Cart> findByUsername(String username);
}

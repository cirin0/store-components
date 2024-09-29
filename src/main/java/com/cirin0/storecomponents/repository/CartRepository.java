package com.cirin0.storecomponents.repository;

import com.cirin0.storecomponents.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
  List<Cart> findByUsername(String username);
}

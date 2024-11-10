package com.cirin0.storecomponents.repository;

import com.cirin0.storecomponents.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
  Optional<CartItem> findByCartIdAndProductId(Long cartId, Long productId);
}

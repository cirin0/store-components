package com.cirin0.storecomponents.repository;

import com.cirin0.storecomponents.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}

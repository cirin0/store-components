package com.cirin0.storecomponents.repository;

import com.cirin0.storecomponents.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}

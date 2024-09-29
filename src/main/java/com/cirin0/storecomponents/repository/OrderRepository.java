package com.cirin0.storecomponents.repository;

import com.cirin0.storecomponents.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
  List<Order> findByUserId(Long userId);
}

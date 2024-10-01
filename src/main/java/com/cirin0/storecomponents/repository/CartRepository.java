package com.cirin0.storecomponents.repository;

import com.cirin0.storecomponents.model.Cart;
import com.cirin0.storecomponents.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
  Optional<Cart> findByUser(User user);
}

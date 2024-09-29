package com.cirin0.storecomponents.service;

import com.cirin0.storecomponents.model.Cart;
import com.cirin0.storecomponents.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

  private final CartRepository cartRepository;

  public CartService(CartRepository cartRepository) {
    this.cartRepository = cartRepository;
  }

 public List<Cart> getUserCart(String username) {
    return cartRepository.findByUsername(username);
  }

  //public Cart deleteProductFromCart(String username, Long productId) {
    //return null;
  //}
}

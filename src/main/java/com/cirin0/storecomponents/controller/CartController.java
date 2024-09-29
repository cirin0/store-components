package com.cirin0.storecomponents.controller;

import com.cirin0.storecomponents.model.Cart;
import com.cirin0.storecomponents.service.CartService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class CartController {
  private final CartService cartService;

  public CartController(CartService cartService) {
    this.cartService = cartService;
  }

  @GetMapping("/cart")
  public List<Cart> getUserCart(String username) {
    return cartService.getUserCart(username);
  }

  //@GetMapping("/cart/{productId}")
  //public Cart deleteProductFromCart(String username, Long productId) {
  //  return cartService.deleteProductFromCart(username, productId);
  //}
}

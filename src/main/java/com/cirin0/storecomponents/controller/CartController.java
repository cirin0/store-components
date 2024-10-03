package com.cirin0.storecomponents.controller;

import com.cirin0.storecomponents.model.Cart;
import com.cirin0.storecomponents.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartController {

  private final CartService cartService;

  @GetMapping
  public String showNotLoggedInMessage() {
    return "Please log in to access your cart";
  }

  @GetMapping("/{cartId}")
  public String showCart(@PathVariable Long cartId) {
    return cartService.getCartByUserId(cartId).toString();
  }

  @PostMapping("/{cartId}")
  public String createCart(@PathVariable Cart cartId) {
    if (cartService.getCartByUserId(cartId.getId()).isPresent()) {
      return "Cart already exists";
    } else if (cartId.getId() == null) {
      return "Cart ID is required";
    } else {
      cartService.createCart(cartId);
      return "Cart created";

    }
  }

  @DeleteMapping("/{cartId}")
  public String deleteCart(@PathVariable Long cartId) {
    cartService.deleteCart(cartId);
    return "Cart deleted";
  }

  @PostMapping("/{cartId}/products/{productId}")
  public String addToCart(@PathVariable Long cartId, @PathVariable Long productId, @RequestParam int quantity) {
    cartService.addProductToCart(cartId, productId, quantity);
    return "Product added to cart";
  }

  @DeleteMapping("/{cartItemId}")
  public String removeFromCart(@PathVariable Long cartItemId) {
    cartService.removeCartItem(cartItemId);
    return "Product removed from cart";
  }

  @PostMapping("/{cartId}/clear")
  public String clearCart(@PathVariable Long cartId) {
    cartService.clearCart(cartId);
    return "Cart cleared";
  }

  @GetMapping("/{cartId}/total")
  public double getCartTotal(@PathVariable Long cartId) {
    return cartService.calculateTotalPrice(cartId);
  }
}

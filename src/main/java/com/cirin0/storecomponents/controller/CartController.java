package com.cirin0.storecomponents.controller;

import com.cirin0.storecomponents.model.Cart;
import com.cirin0.storecomponents.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class CartController {

  private final CartService cartService;

  @GetMapping
  public String showNotLoggedInMessage() {
    return "Please log in to access your cart";
  }

  @GetMapping("/{cartId}")
  public Cart getCartById(@PathVariable Long cartId) {
    return cartService.getCartById(cartId);
  }

  @PostMapping("/create")
  public ResponseEntity<Cart> createCart(@RequestParam("userId") Long userId) {
    Cart cart = cartService.createCart(userId);
    return ResponseEntity.ok(cart);
  }

  @DeleteMapping("/{cartId}")
  public ResponseEntity<Void> deleteCart(@PathVariable Long cartId) {
    cartService.deleteCart(cartId);
    return ResponseEntity.noContent().build();
  }

  @PostMapping("/{cartId}/items")
  public ResponseEntity<Cart> addItemsToCart(
      @PathVariable Long cartId,
      @RequestParam Long productId,
      @RequestParam Integer quantity
  ) {
    Cart updatedCart = cartService.addProductToCart(cartId, productId, quantity);
    return ResponseEntity.ok(updatedCart);
  }

  /*@DeleteMapping("/{cartItemId}")
  public String removeFromCart(@PathVariable Long cartItemId) {
    cartService.removeCartItem(cartItemId);
    return "Product removed from cart";
  }

  @PostMapping("/{cartId}/clear")
  public String clearCart(@PathVariable Long cartId) {
    cartService.clearCart(cartId);
    return "Cart cleared";
  }

   */

  @GetMapping("/{cartId}/total")
  public ResponseEntity<Double> getTotal(@PathVariable Long cartId) {
    return ResponseEntity.ok(cartService.calculateTotalPrice(cartId));
  }
}

package com.cirin0.storecomponents.controller.api;

import com.cirin0.storecomponents.dto.AddToCartDTO;
import com.cirin0.storecomponents.dto.CartDTO;
import com.cirin0.storecomponents.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class CartController {

  private final CartService cartService;

  @GetMapping
  public ResponseEntity<CartDTO> getCart(@RequestParam("userId") Long userId) { // @AuthenticationPrincipal User user
    return ResponseEntity.ok(cartService.getCartByUserId(userId));
  }

  @PostMapping("/add")
  public ResponseEntity<CartDTO> addToCart(
      @RequestParam("userId") Long userId,
      @RequestBody AddToCartDTO addToCartDTO) {
    return ResponseEntity.ok(cartService.addToCart(userId, addToCartDTO));
  }

  @PutMapping("/update/{productId}")
  public ResponseEntity<CartDTO> updateQuantity(
      @RequestParam("userId") Long userId,
      @PathVariable Long productId,
      @RequestParam Integer quantity) {
    return ResponseEntity.ok(cartService.updateCartItemQuantity(userId, productId, quantity));
  }

  @DeleteMapping("/remove/{productId}")
  public ResponseEntity<CartDTO> removeFromCart(
      @RequestParam("userId") Long userId,
      @PathVariable Long productId) {
    return ResponseEntity.ok(cartService.removeFromCart(userId, productId));
  }

  @DeleteMapping("/clear")
  public ResponseEntity<Void> clearCart(@RequestParam("userId") Long userId) {
    cartService.clearCart(userId);
    return ResponseEntity.noContent().build();
  }
}

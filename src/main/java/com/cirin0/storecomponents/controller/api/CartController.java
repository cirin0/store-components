package com.cirin0.storecomponents.controller.api;

import com.cirin0.storecomponents.dto.AddToCartDTO;
import com.cirin0.storecomponents.dto.CartDTO;
import com.cirin0.storecomponents.dto.CartItemDTO;
import com.cirin0.storecomponents.mapper.CartMapper;
import com.cirin0.storecomponents.model.Cart;
import com.cirin0.storecomponents.model.User;
import com.cirin0.storecomponents.repository.CartRepository;
import com.cirin0.storecomponents.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class CartController {

  private final CartService cartService;
  private final CartMapper cartMapper;
  private final CartRepository cartRepository;

  @GetMapping
  public ResponseEntity<CartDTO> getCart(@RequestParam("userId") Long userId) { // @AuthenticationPrincipal User user
    return ResponseEntity.ok(cartService.getCartByUserId(userId));
  }

  @PostMapping("/add")
  public ResponseEntity<CartDTO> addToCart(
      @RequestParam("userId") Long userId,
      @RequestBody AddToCartDTO addToCartDTO) {
    return ResponseEntity.ok(cartService.addProductToCart(userId, addToCartDTO));
  }

  @PutMapping("/update/{productId}")
  public ResponseEntity<CartDTO> updateQuantity(
      @AuthenticationPrincipal User user,
      @PathVariable Long productId,
      @RequestParam Integer quantity) {
    return ResponseEntity.ok(cartService.updateCartItemQuantity(user.getId(), productId, quantity));
  }

  @DeleteMapping("/remove/{productId}")
  public ResponseEntity<CartDTO> removeFromCart(
      @AuthenticationPrincipal User user,
      @PathVariable Long productId) {
    return ResponseEntity.ok(cartService.removeFromCart(user.getId(), productId));
  }

  @DeleteMapping("/clear")
  public ResponseEntity<Void> clearCart(@AuthenticationPrincipal User user) {
    cartService.clearCart(user.getId());
    return ResponseEntity.noContent().build();
  }
}

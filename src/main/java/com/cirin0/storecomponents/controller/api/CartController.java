package com.cirin0.storecomponents.controller.api;

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
    Optional<Cart> existingCart = cartRepository.findByUserId(userId);
    return existingCart.map(cartMapper::toDto)
        .map(ResponseEntity.status(201)::body)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping("/items")
  public CartDTO addItemToCart(@AuthenticationPrincipal User user, @RequestBody CartItemDTO cartItemDTO) {
    return cartService.addItemsToCart(user.getId(), cartItemDTO.getProductId(), cartItemDTO.getQuantity());
  }

  @PutMapping("/items/{cartItemId}")
  public ResponseEntity<CartDTO> updateCartItemQuantity(@RequestParam Long userId, @PathVariable Long cartItemId, @RequestParam int quantity) {
    return ResponseEntity.ok(cartService.UpdateCartItemQuantity(userId, cartItemId, quantity));
  }

  @DeleteMapping("/items/{cartItemId}")
  public void deleteItemFromCart(@RequestParam Long userId, @PathVariable Long cartItemId) {
    cartService.deleteItemFromCart(userId, cartItemId);
  }
}

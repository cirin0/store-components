package com.cirin0.storecomponents.service;

import com.cirin0.storecomponents.model.Cart;
import com.cirin0.storecomponents.model.CartItem;
import com.cirin0.storecomponents.model.Product;
import com.cirin0.storecomponents.repository.CartItemRepository;
import com.cirin0.storecomponents.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
  private final CartRepository cartRepository;
  private final CartItemRepository cartItemRepository;
  private final ProductService productService;

  public Optional<Cart> getCartByUserId(Long userId) {
    return cartRepository.findByUserId(userId);
  }

  public Cart createCart(Cart cart) {
    return cartRepository.save(cart);
  }

  public void deleteCart(Long id) {
    cartRepository.deleteById(id);
  }

  public CartItem addProductToCart(Long cartId, Long productId, int quantity) {
    Cart cart = cartRepository.findById(cartId)
        .orElseThrow(() -> new RuntimeException("Cart not found with id " + cartId));

    Optional<CartItem> existingCartItem = cart.getCartItems().stream()
        .filter(ci -> ci.getProduct().getId().equals(productId))
        .findFirst();

    if (existingCartItem.isPresent()) {
      CartItem itemToUpdate = existingCartItem.get();
      itemToUpdate.setQuantity(itemToUpdate.getQuantity() + quantity);
      return cartItemRepository.save(itemToUpdate);
    } else {
      Product product = productService.getProductById(productId);
      if (product == null) {
        throw new RuntimeException("Product not found with id " + productId);
      }
      // TODO - fix maybe

      //.orElseThrow(() -> new RuntimeException("Product not found with id " + productId));

      CartItem newCartItem = CartItem.builder()
          .cart(cart)
          .product(product)
          .quantity(quantity)
          .build();

      return cartItemRepository.save(newCartItem);
    }
  }

  public void removeCartItem(Long cartItemId) {
    cartItemRepository.deleteById(cartItemId);
  }

  public void clearCart(Long cartId) {
    Cart cart = cartRepository.findById(cartId)
        .orElseThrow(() -> new RuntimeException("Cart not found " + cartId));
    cart.getCartItems().clear();
    cartRepository.save(cart);
  }

  public double calculateTotalPrice(Long cartId) {
    Cart cart = cartRepository.findById(cartId)
        .orElseThrow(() -> new RuntimeException("Cart not found " + cartId));
    return cart.getCartItems().stream()
        .mapToDouble(cartItem -> cartItem.getProduct().getPrice() * cartItem.getQuantity())
        .sum();
  }
}

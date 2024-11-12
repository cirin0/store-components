package com.cirin0.storecomponents.service;

import com.cirin0.storecomponents.dto.AddToCartDTO;
import com.cirin0.storecomponents.dto.CartDTO;
import com.cirin0.storecomponents.mapper.CartMapper;
import com.cirin0.storecomponents.model.Cart;
import com.cirin0.storecomponents.model.CartItem;
import com.cirin0.storecomponents.model.Product;
import com.cirin0.storecomponents.model.User;
import com.cirin0.storecomponents.repository.CartRepository;
import com.cirin0.storecomponents.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CartService {
  private final CartRepository cartRepository;
  private final CartMapper cartMapper;
  private final ProductRepository productRepository;

  public CartDTO getCartById(Long id) {
    Cart cart = cartRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Cart not found " + id));
    return cartMapper.toDto(cart);
  }

  public CartDTO getCartByUserId(Long userId) {
    Cart cart = cartRepository.findByUserId(userId)
        .orElseGet(() -> {
          Cart newCart = new Cart();
          newCart.setUser(User.builder().id(userId).build());
          return cartRepository.save(newCart);
        });
    return cartMapper.toDto(cart);
  }

  public CartDTO addToCart(Long userId, AddToCartDTO addToCartDTO) {
    Cart cart = cartRepository.findByUserId(userId)
        .orElseGet(() -> {
          Cart newCart = new Cart();
          newCart.setUser(User.builder().id(userId).build());
          return cartRepository.save(newCart);
        });
    Product product = productRepository.findById(addToCartDTO.getProductId())
        .orElseThrow(() -> new RuntimeException("Product not found " + addToCartDTO.getProductId()));
    Optional<CartItem> existingItem = cart.getItems().stream()
        .filter(item -> item.getProduct().getId().equals(addToCartDTO.getProductId()))
        .findFirst();
    if (existingItem.isPresent()) {
      existingItem.get().setQuantity(existingItem.get().getQuantity() + addToCartDTO.getQuantity());
    } else {
      CartItem newItem = new CartItem();
      newItem.setCart(cart);
      newItem.setProduct(product);
      newItem.setQuantity(addToCartDTO.getQuantity());
      cart.getItems().add(newItem);
    }
    cart.recalculateTotal();
    cartRepository.save(cart);
    return cartMapper.toDto(cart);
  }

  public CartDTO updateCartItemQuantity(Long userId, Long productId, Integer quantity) {
    Cart cart = cartRepository.findByUserId(userId)
        .orElseThrow(() -> new RuntimeException("Cart not found " + userId));
    CartItem cartItem = cart.getItems().stream()
        .filter(item -> item.getProduct().getId().equals(productId))
        .findFirst()
        .orElseThrow(() -> new RuntimeException("Product not found in cart"));
    cartItem.setQuantity(quantity);
    cart.recalculateTotal();
    cartRepository.save(cart);
    return cartMapper.toDto(cart);
  }

  public CartDTO removeFromCart(Long userId, Long productId) {
    Cart cart = cartRepository.findByUserId(userId)
        .orElseThrow(() -> new RuntimeException("Cart not found " + userId));
    cart.getItems().removeIf(item -> item.getProduct().getId().equals(productId));
    cart.recalculateTotal();
    cartRepository.save(cart);
    return cartMapper.toDto(cart);
  }

  public void clearCart(Long userId) {
    Cart cart = cartRepository.findByUserId(userId)
        .orElseThrow(() -> new RuntimeException("Cart not found " + userId));
    cart.getItems().clear();
    cart.setTotalPrice(0.0);
    cartRepository.save(cart);
  }
}

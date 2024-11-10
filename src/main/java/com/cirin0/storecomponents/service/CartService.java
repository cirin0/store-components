package com.cirin0.storecomponents.service;

import com.cirin0.storecomponents.dto.AddToCartDTO;
import com.cirin0.storecomponents.dto.CartDTO;
import com.cirin0.storecomponents.dto.product.ProductDTO;
import com.cirin0.storecomponents.mapper.CartMapper;
import com.cirin0.storecomponents.mapper.ProductMapper;
import com.cirin0.storecomponents.model.Cart;
import com.cirin0.storecomponents.model.CartItem;
import com.cirin0.storecomponents.repository.CartItemRepository;
import com.cirin0.storecomponents.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
@RequiredArgsConstructor
public class CartService {
  private final CartRepository cartRepository;
  private final ProductService productService;
  private final CartMapper cartMapper;
  private final ProductMapper productMapper;
  private final CartItemRepository cartItemRepository;

  public CartDTO getCartByUserId(Long id) {
    Cart cart = cartRepository.findByUserId(id)
        .orElseThrow(() -> new RuntimeException("Cart not found " + id));
    return cartMapper.toDto(cart);
  }

  public CartDTO addProductToCart(Long userId, AddToCartDTO addToCartDTO) {
    Cart cart = cartRepository.findByUserId(userId)
        .orElseThrow(() -> new RuntimeException("Cart not found " + userId));
    ProductDTO productDTO = productService.getProductById(addToCartDTO.getProductId());
    CartItem cartItem = cartItemRepository
        .findByCartIdAndProductId(cart.getId(), productDTO.getId())
        .orElseGet(() -> {
          CartItem newItem = CartItem.builder()
              .cart(cart)
              .product(productMapper.toEntity(productDTO))
              .price(BigDecimal.valueOf(productDTO.getPrice()))
              .quantity(0)
              .build();
          cart.getItems().add(newItem);
          return newItem;
        });

    cartItem.setQuantity(cartItem.getQuantity() + addToCartDTO.getQuantity());
    cartItemRepository.save(cartItem);
    cart.recalculateTotal();
    cartRepository.save(cart);
    return cartMapper.toDto(cart);
  }

  public CartDTO updateCartItemQuantity(Long userId, Long productId, Integer quantity) {
    Cart cart = cartRepository.findByUserId(userId)
        .orElseThrow(() -> new RuntimeException("Cart not found " + userId));
    CartItem cartItem = cartItemRepository
        .findByCartIdAndProductId(cart.getId(), productId)
        .orElseThrow(() -> new RuntimeException("Product not found in cart"));

    if (quantity > 0) {
      cartItem.setQuantity(quantity);
      cartItemRepository.save(cartItem);
    } else {
      cart.getItems().remove(cartItem);
      cartItemRepository.delete(cartItem);
    }
    cart.recalculateTotal();
    return cartMapper.toDto(cart);
  }

//  public CartDTO removeProductFromCart(Long userId, Long productId) {
//    Cart cart = getOrCreateCart(userId);
//    CartItem cartItem = cartItemRepository
//        .findByCartIdAndProductId(cart.getId(), productId)
//        .orElseThrow(() -> new RuntimeException("Product not found in cart"));
//    cart.getItems().remove(cartItem);
//    cartItemRepository.delete(cartItem);
//    cart.recalculateTotal();
//    return cartMapper.toDto(cart);
//  }

  public CartDTO removeFromCart(Long userId, Long productId) {
    return updateCartItemQuantity(userId, productId, 0);
  }

  public void clearCart(Long userId) {
    Cart cart = cartRepository.findByUserId(userId)
        .orElseThrow(() -> new RuntimeException("Cart not found " + userId));
    cartItemRepository.deleteAll(cart.getItems());
    cart.getItems().clear();
    cart.setTotalPrice(BigDecimal.ZERO);
    cartRepository.save(cart);
  }
}
